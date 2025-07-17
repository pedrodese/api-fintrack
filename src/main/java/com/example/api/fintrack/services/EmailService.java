package com.example.api.fintrack.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public void sendWelcomeEmail(String toEmail, String userName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromName + " <" + fromEmail + ">");
            message.setTo(toEmail);
            message.setSubject("Bem-vindo ao FinTrack! 🎉");
            message.setText(createWelcomeEmailContent(userName));
            
            mailSender.send(message);
            log.info("Email de boas-vindas enviado com sucesso para: {}", toEmail);
        } catch (Exception e) {
            log.error("Erro ao enviar email de boas-vindas para {}: {}", toEmail, e.getMessage());
            // Não lançar exceção para não quebrar o fluxo de registro
        }
    }

    private String createWelcomeEmailContent(String userName) {
        return String.format(
            "Olá %s! 👋\n\n" +
            "Seja muito bem-vindo(a) ao FinTrack! 🎉\n\n" +
            "Estamos muito felizes em tê-lo(a) conosco nesta jornada de controle financeiro.\n\n" +
            "Com o FinTrack você poderá:\n" +
            "✅ Controlar suas receitas e despesas\n" +
            "✅ Criar orçamentos mensais\n" +
            "✅ Definir e acompanhar metas financeiras\n" +
            "✅ Visualizar relatórios detalhados\n" +
            "✅ Receber notificações importantes\n\n" +
            "Comece agora mesmo criando suas primeiras categorias e transações!\n\n" +
            "Se tiver alguma dúvida, não hesite em entrar em contato conosco.\n\n" +
            "Atenciosamente,\n" +
            "Equipe FinTrack"
        );
    }
} 