package bakend.user.infra;

public class MessageHtml {
  

    public static String getTemplate(String nombre, String email, String code, String mensaje) {
        String template = """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="UTF-8">
                  <title>Mensaje de Contacto</title>
                </head>
                <body style="font-family: Arial, sans-serif; color:#333; max-width:600px; margin:auto; border:1px solid #ddd; border-radius:8px; padding:20px;">
                  
                  <h2 style="color:#4CAF50; text-align:center;">📩 Nuevo mensaje recibido</h2>
                  
                  <p><strong>Nombre:</strong> {{nombre}}</p>
                  <p><strong>Email:</strong> {{email}}</p>
                  <p><strong>Código:</strong> {{code}}</p>
                  
                  <hr style="border:1px solid #eee; margin:20px 0;">
                  
                  <p><strong>Mensaje:</strong></p>
                  <div style="background:#f9f9f9; padding:15px; border-radius:5px; line-height:1.5;">
                    {{mensaje}}
                  </div>
                  
                  <hr style="border:1px solid #eee; margin:20px 0;">
                  
                  <p style="font-size:12px; color:#777; text-align:center;">
                    ✨ Este correo fue generado automáticamente por tu sistema.
                  </p>
                </body>
                </html>
                """;

        // Reemplazar los índices dinámicos
        template = template.replace("{{nombre}}", nombre);
        template = template.replace("{{email}}", email);
        template = template.replace("{{code}}", code);
        template = template.replace("{{mensaje}}", mensaje);

        return template;
    }
}
