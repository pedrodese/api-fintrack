package com.example.api.fintrack.application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.email.from-name:FinTrack}")
    private String fromName;

    public void sendWelcomeEmail(String toEmail, String userName, String verificationCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromName + " <" + fromEmail + ">");
            helper.setTo(toEmail);
            helper.setSubject("Bem-vindo ao FinTrack! 🎉");
            helper.setText(createWelcomeEmailHtml(userName, verificationCode), true); // true = HTML

            mailSender.send(message);
            log.info("Email de boas-vindas com código de verificação enviado com sucesso para: {}", toEmail);
        } catch (Exception e) {
            log.error("Erro ao enviar email de boas-vindas para {}: {}", toEmail, e.getMessage());
        }
    }

    private String createWelcomeEmailHtml(String userName, String verificationCode) {
        return """
        <html>
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <style>
            @media only screen and (max-width: 600px) {
              .container {
                padding: 24px !important;
              }
              .grid {
                display: block !important;
              }
              .card {
                margin-bottom: 16px !important;
              }
              .button {
                width: 100%% !important;
                padding: 16px 0 !important;
              }
            }
          </style>
        </head>
        <body style="margin:0;padding:0;background:#181A1B;font-family:'Inter', sans-serif;color:#F3F3F3;">
          <div class="container" style="max-width:620px;margin:auto;background:#23272A;border-radius:16px;padding:40px;box-shadow:0 8px 20px rgba(0,0,0,0.4);">
    
            <h1 style="color:#00E676;font-size:28px;margin-top:0;margin-bottom:16px;">Bem-vindo(a) ao FinTrack, %s! 👋</h1>
            <p style="font-size:17px;line-height:1.6;margin-bottom:24px;">Sua jornada rumo a uma vida financeira mais organizada e inteligente começa agora.</p>
    
            <div style="background:#2A2E30;border-radius:12px;padding:24px;margin:24px 0;text-align:center;border:2px solid #00E676;">
              <h2 style="color:#00E676;font-size:20px;margin-top:0;margin-bottom:16px;">🔐 Confirme sua conta</h2>
              <p style="font-size:16px;margin-bottom:16px;">Use o código abaixo para ativar sua conta:</p>
              <div style="background:#181A1B;border-radius:8px;padding:16px;margin:16px 0;display:inline-block;">
                <span style="font-size:32px;font-weight:bold;color:#00E676;letter-spacing:8px;">%s</span>
              </div>
              <p style="font-size:14px;color:#B0B0B0;margin-top:16px;">Este código expira em 10 minutos</p>
            </div>
    
            <hr style="border:none;border-top:1px solid #2F3336;margin:32px 0;">
    
            <h2 style="font-size:20px;margin-bottom:16px;">🧭 O que você poderá fazer com o FinTrack?</h2>
    
            <div class="grid" style="display:grid;grid-template-columns:1fr 1fr;gap:16px;">
              <div class="card" style="background:#2A2E30;border-radius:12px;padding:16px;">📊 <strong>Planeje seus gastos</strong><br><span style="font-size:14px;">Defina limites por categoria e receba alertas.</span></div>
              <div class="card" style="background:#2A2E30;border-radius:12px;padding:16px;">💰 <strong>Controle receitas e despesas</strong><br><span style="font-size:14px;">Registre, edite e visualize tudo com filtros.</span></div>
              <div class="card" style="background:#2A2E30;border-radius:12px;padding:16px;">🎯 <strong>Crie metas financeiras</strong><br><span style="font-size:14px;">Economize com propósito e acompanhe seu progresso.</span></div>
              <div class="card" style="background:#2A2E30;border-radius:12px;padding:16px;">📈 <strong>Veja sua evolução</strong><br><span style="font-size:14px;">Gráficos e comparativos para decisões melhores.</span></div>
              <div class="card" style="background:#2A2E30;border-radius:12px;padding:16px;">📥 <strong>Importe extratos</strong><br><span style="font-size:14px;">Conecte-se com bancos ou faça uploads CSV.</span></div>
              <div class="card" style="background:#2A2E30;border-radius:12px;padding:16px;">🧠 <strong>Melhore seu Score</strong><br><span style="font-size:14px;">Receba um índice financeiro e dicas práticas.</span></div>
            </div>
    
            <hr style="border:none;border-top:1px solid #2F3336;margin:40px 0;">
    
            <p style="font-size:15px;margin-bottom:8px;">📬 Se tiver dúvidas, basta responder este e-mail — estamos aqui para ajudar.</p>
            <p style="color:#00E676;font-weight:600;margin:0;">Equipe FinTrack 💚</p>
    
          </div>
        </body>
        </html>
        """.formatted(userName, verificationCode);
    }
    
    
    
    
} 