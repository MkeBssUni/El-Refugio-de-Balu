package com.balu.backend.kernel.templates;

public class EmailTemplates {
    public static String newAccountTemplate(String code){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "  <title></title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!--><!--<![endif]-->\n" +
                "  <style>\n" +
                "    * {\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      line-height: inherit\n" +
                "    }\n" +
                "\n" +
                "    .desktop_hide,\n" +
                "    .desktop_hide table {\n" +
                "      mso-hide: all;\n" +
                "      display: none;\n" +
                "      max-height: 0px;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    .image_block img+div {\n" +
                "      display: none;\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width:520px) {\n" +
                "      .desktop_hide table.icons-inner {\n" +
                "        display: inline-block !important;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner td {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        display: none;\n" +
                "      }\n" +
                "\n" +
                "      .row-content {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      .stack .column {\n" +
                "        width: 100%;\n" +
                "        display: block;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        min-height: 0;\n" +
                "        max-height: 0;\n" +
                "        max-width: 0;\n" +
                "        overflow: hidden;\n" +
                "        font-size: 0px;\n" +
                "      }\n" +
                "\n" +
                "      .desktop_hide,\n" +
                "      .desktop_hide table {\n" +
                "        display: table !important;\n" +
                "        max-height: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #118a95; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #118a95;\">\n" +
                "  <tbody>\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px; margin: 0 auto;\" width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                  <table class=\"text_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div style=\"font-family: sans-serif\">\n" +
                "                          <div class style=\"font-size: 12px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #fff; line-height: 1.2;\">\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 16.8px;\"><span style=\"font-size:22px;\"><strong>Bienvenido</strong></span></p>\n" +
                "                          </div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table class=\"divider_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div class=\"alignment\" align=\"center\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                            <tr>\n" +
                "                              <td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;\"><span>&#8202;</span></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table class=\"text_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div style=\"font-family: sans-serif\">\n" +
                "                          <div class style=\"font-size: 12px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #fff; line-height: 1.2;\">\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 16.8px;\">Haz sido registrado correctamente en&nbsp;<em>El Refugio de Balu</em>, para activar tu cuenta ingresa el siguiente código:</p>\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 14.399999999999999px;\">&nbsp;</p>\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 16.8px;\"><span style=\"font-size:20px;\"><em><u><strong>"+code+"</strong></u></em></span></p>\n" +
                "                          </div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "                <td class=\"column column-2\" width=\"33.333333333333336%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                  <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\" style=\"width:100%;\">\n" +
                "                        <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                          <div style=\"max-width: 166.667px;\"><img src=\"https://1253e04968.imgdist.com/pub/bfra/k5n65t20/roo/m09/8p4/logo%20blanco.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"166.667\" alt=\"I'm an image\" title=\"I'm an image\"></div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "</html>";
    }
    public static String newPasswordTemplate(String code){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "  <title></title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!--><!--<![endif]-->\n" +
                "  <style>\n" +
                "    * {\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      line-height: inherit\n" +
                "    }\n" +
                "\n" +
                "    .desktop_hide,\n" +
                "    .desktop_hide table {\n" +
                "      mso-hide: all;\n" +
                "      display: none;\n" +
                "      max-height: 0px;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    .image_block img+div {\n" +
                "      display: none;\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width:520px) {\n" +
                "      .desktop_hide table.icons-inner {\n" +
                "        display: inline-block !important;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner td {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        display: none;\n" +
                "      }\n" +
                "\n" +
                "      .row-content {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      .stack .column {\n" +
                "        width: 100%;\n" +
                "        display: block;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        min-height: 0;\n" +
                "        max-height: 0;\n" +
                "        max-width: 0;\n" +
                "        overflow: hidden;\n" +
                "        font-size: 0px;\n" +
                "      }\n" +
                "\n" +
                "      .desktop_hide,\n" +
                "      .desktop_hide table {\n" +
                "        display: table !important;\n" +
                "        max-height: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #118a95; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #118a95;\">\n" +
                "  <tbody>\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px; margin: 0 auto;\" width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                  <table class=\"text_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div style=\"font-family: sans-serif\">\n" +
                "                          <div class style=\"font-size: 12px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #fff; line-height: 1.2;\">\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 16.8px;\"><span style=\"font-size:22px;\"><strong>Bienvenido</strong></span></p>\n" +
                "                          </div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table class=\"divider_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div class=\"alignment\" align=\"center\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                            <tr>\n" +
                "                              <td class=\"divider_inner\" style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;\"><span>&#8202;</span></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table class=\"text_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div style=\"font-family: sans-serif\">\n" +
                "                          <div class style=\"font-size: 12px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #fff; line-height: 1.2;\">\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 16.8px;\">Haz solicitado un cambio de contraseña en&nbsp;<em>El Refugio de Balu</em>, ingresa este código:</p>\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 14.399999999999999px;\">&nbsp;</p>\n" +
                "                            <p style=\"margin: 0; font-size: 14px; text-align: left; mso-line-height-alt: 16.8px;\"><span style=\"font-size:20px;\"><em><u><strong>"+code+"</strong></u></em></span></p>\n" +
                "                          </div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "                <td class=\"column column-2\" width=\"33.333333333333336%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                  <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\" style=\"width:100%;\">\n" +
                "                        <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                          <div style=\"max-width: 166.667px;\"><img src=\"https://1253e04968.imgdist.com/pub/bfra/k5n65t20/roo/m09/8p4/logo%20blanco.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"166.667\" alt=\"I'm an image\" title=\"I'm an image\"></div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "</html>";
    }

    public static String adoptionApprovalTemplate(String namePet){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "<head>\n" +
                "  <title></title>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                "  <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "  <!--<![endif]-->\n" +
                "  <style>\n" +
                "    * {\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      font-family: 'Baloo'!important;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      line-height: inherit\n" +
                "    }\n" +
                "\n" +
                "    .desktop_hide,\n" +
                "    .desktop_hide table {\n" +
                "      mso-hide: all;\n" +
                "      display: none;\n" +
                "      max-height: 0px;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    .image_block img+div {\n" +
                "      display: none;\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width:700px) {\n" +
                "      .desktop_hide table.icons-inner {\n" +
                "        display: inline-block !important;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner td {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        display: none;\n" +
                "      }\n" +
                "\n" +
                "      .row-content {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      .stack .column {\n" +
                "        width: 100%;\n" +
                "        display: block;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        min-height: 0;\n" +
                "        max-height: 0;\n" +
                "        max-width: 0;\n" +
                "        overflow: hidden;\n" +
                "        font-size: 0px;\n" +
                "      }\n" +
                "\n" +
                "      .desktop_hide,\n" +
                "      .desktop_hide table {\n" +
                "        display: table !important;\n" +
                "        max-height: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #ffefe0; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffefe0;\">\n" +
                "  <tbody>\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #0068a5;\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                  <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\" style=\"width:100%;\">\n" +
                "                        <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                          <div style=\"max-width: 340px;\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/5651/puppy_sheet.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"340\"></div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "                <td class=\"column column-2\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                  <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"15\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                          <div style=\"max-width: 102px;\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/kki/p4h/cw5/13f04ba8-01cc-422d-af1f-711f8454888c.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"102\"></div>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\" style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:50px;text-align:center;width:100%;\">\n" +
                "                        <h1 style=\"margin: 0; color: #f0f0f0; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 40px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 48px;\"><span class=\"tinyMce-placeholder\">¡Felicitaciones!</span></h1>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table class=\"heading_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\" style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;text-align:center;width:100%;\">\n" +
                "                        <h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 33px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 39.6px;\"><span class=\"tinyMce-placeholder\">&nbsp;Su solicitud de adopción ha sido aprobada tu mascota "+namePet+" te espera en casa</span></h1>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String finalizeAdoptionTemplate(String namePet){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img+div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width:700px) {\n" +
                "            .desktop_hide table.icons-inner {\n" +
                "                display: inline-block !important;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner td {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #ffefe0; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffefe0;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #0068a5;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <div class=\"spacer_block block-2\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <table class=\"image_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 340px;\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6786/dog_tshirt.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"340\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"15\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 102px;\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/kki/p4h/cw5/13f04ba8-01cc-422d-af1f-711f8454888c.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"102\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:50px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f0f0f0; direction: ltr; font-family: 'Baloo'; font-size: 40px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 48px;\"><span class=\"tinyMce-placeholder\">¡Lo Sentimos!</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"heading_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: 'Baloo'; font-size: 33px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 39.6px;\"><span class=\"tinyMce-placeholder\">&nbsp;Su solicitud de adopción de "+namePet+" ha sido finalizada</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String ActiveRequestTemplate(int countRequest,String namePet){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: 'Baloo' !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img+div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width:700px) {\n" +
                "            .desktop_hide table.icons-inner {\n" +
                "                display: inline-block !important;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner td {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #f7e3d6; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f7e3d6;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ee7240; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-2\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-left: 15px; padding-right: 15px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"heading_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f0f0f0; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 30px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 45px;\"><span class=\"tinyMce-placeholder\">El Refugio de Balu</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6666/ac2d417d-fadb-49b8-a039-2a89e4002d44.png'); background-repeat: no-repeat; color: #000000; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"33.333333333333336%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-top:25px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f3f2f7; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 29px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 43.5px;\"><span class=\"tinyMce-placeholder\">Tienes  <b>"+countRequest+ "</b>  solicitudes de adopción de "+namePet+" pendientes de revisión .</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 453.333px;\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/kwb/3ks/pxe/5299de11-6e9f-42d5-a5e5-84e50e415c17.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"453.333\" alt=\"Woman and Dog\" title=\"Woman and Dog\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ffffff; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"icons_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
                "                                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                                    <tr>\n" +
                "                                                        <td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </table>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";

    }
    public static String PasswordChangedTemplate(){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img+div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width:700px) {\n" +
                "            .desktop_hide table.icons-inner {\n" +
                "                display: inline-block !important;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner td {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #f7e3d6; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f7e3d6;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ee7240; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"33.333333333333336%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-left: 15px; padding-right: 15px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                                                <div class=\"alignment\" align=\"right\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 78.667px;\"><a href=\"http://example.com\" target=\"_blank\" style=\"outline:none\" tabindex=\"-1\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/cvn/utv/472/bf95ebca-b8e2-4f45-85c1-a2d359c60bd2.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"78.667\" alt=\"El refugio de balu\" title=\"El refugio de balu\"></a></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-left: 15px; padding-right: 15px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"heading_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f0f0f0; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 30px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 45px;\"><span class=\"tinyMce-placeholder\">El Refugio de Balu</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6666/ac2d417d-fadb-49b8-a039-2a89e4002d44.png'); background-repeat: no-repeat; color: #000000; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"33.333333333333336%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-top:25px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f3f2f7; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 29px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 43.5px;\"><span class=\"tinyMce-placeholder\">¡Tu contraseña ha sido cambiada exitosamente!.</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"button_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:left;\">\n" +
                "                                                <div class=\"alignment\" align=\"left\"><!--[if mso]>\n" +
                "                                                    <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://example.com\" style=\"height:52px;width:183px;v-text-anchor:middle;\" arcsize=\"97%\" stroke=\"false\" fillcolor=\"#e08f04\">\n" +
                "                                                        <w:anchorlock/>\n" +
                "                                                        <v:textbox inset=\"0px,0px,0px,0px\">\n" +
                "                                                            <center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:14px\"> <!-- Change font size here -->\n" +
                "                                                    <![endif]-->\n" +
                "                                                    <!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 453.333px;\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/kwb/3ks/pxe/5299de11-6e9f-42d5-a5e5-84e50e415c17.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"453.333\" alt=\"Woman and Dog\" title=\"Woman and Dog\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ffffff; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"icons_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
                "                                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                                    <tr>\n" +
                "                                                        <td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </table>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
    public static String PetRejectedTemplate(String petName){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img+div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width:700px) {\n" +
                "            .desktop_hide table.icons-inner {\n" +
                "                display: inline-block !important;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner td {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #ffefe0; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffefe0;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #0068a5;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <div class=\"spacer_block block-2\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <table class=\"image_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 340px;\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6786/dog_tshirt.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"340\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"15\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 102px;\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/kki/p4h/cw5/13f04ba8-01cc-422d-af1f-711f8454888c.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"102\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:50px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f0f0f0; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 40px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 48px;\"><span class=\"tinyMce-placeholder\">¡Lo Sentimos!</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"heading_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 33px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 39.6px;\"><span class=\"tinyMce-placeholder\">&nbsp;La mascota "+petName+" ha sido dada de baja de nuestro sistema.</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }
    public static String newComment(String petName){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img+div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width:700px) {\n" +
                "            .desktop_hide table.icons-inner {\n" +
                "                display: inline-block !important;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner {\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "\n" +
                "            .icons-inner td {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #f7e3d6; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f7e3d6;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ee7240; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"33.333333333333336%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-left: 15px; padding-right: 15px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                                                <div class=\"alignment\" align=\"right\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 78.667px;\"><a href=\"http://example.com\" target=\"_blank\" style=\"outline:none\" tabindex=\"-1\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/cvn/utv/472/bf95ebca-b8e2-4f45-85c1-a2d359c60bd2.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"78.667\" alt=\"El refugio de balu\" title=\"El refugio de balu\"></a></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-left: 15px; padding-right: 15px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"heading_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f0f0f0; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 30px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 45px;\"><span class=\"tinyMce-placeholder\">El Refugio de Balu</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ee7240; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6666/ac2d417d-fadb-49b8-a039-2a89e4002d44.png'); background-repeat: no-repeat; color: #000000; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"33.333333333333336%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-top:25px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #f3f2f7; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 29px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 43.5px;\"><span class=\"tinyMce-placeholder\">Tienes nuevos comentarios en la mascota "+petName+"</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"button_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:left;\">\n" +
                "                                                <div class=\"alignment\" align=\"left\"><!--[if mso]>\n" +
                "                                                    <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://example.com\" style=\"height:52px;width:183px;v-text-anchor:middle;\" arcsize=\"97%\" stroke=\"false\" fillcolor=\"#e08f04\">\n" +
                "                                                        <w:anchorlock/>\n" +
                "                                                        <v:textbox inset=\"0px,0px,0px,0px\">\n" +
                "                                                            <center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:14px\"> <!-- Change font size here -->\n" +
                "                                                    <![endif]-->\n" +
                "                                                    <!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"66.66666666666667%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 453.333px;\"><img src=\"https://1c21c8727b.imgdist.com/pub/bfra/y8he417t/kwb/3ks/pxe/5299de11-6e9f-42d5-a5e5-84e50e415c17.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"453.333\" alt=\"Woman and Dog\" title=\"Woman and Dog\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ffffff; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"icons_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
                "                                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                                    <tr>\n" +
                "                                                        <td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </table>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
    public static String requestPetDischarge(String petName){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img+div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width:660px) {\n" +
                "            .image_block div.fullWidth {\n" +
                "                max-width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #ffffe9; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffe9;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ff9900;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ff9900; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:10px;padding-top:10px;width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div class=\"fullWidth\" style=\"max-width: 320px;\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6916/2_objects_sdjffhkadf.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"320\" alt=\"Dog with toy\" title=\"Dog with toy\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                                <td class=\"column column-2\" width=\"50%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <div class=\"spacer_block block-1\" style=\"height:55px;line-height:55px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <table class=\"image_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 128px;\"><img src=\"https://d15k2d11r6t6rl.cloudfront.net/pub/bfra/y8he417t/og7/l0x/9x0/logo%20blanco.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"128\"></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <div class=\"spacer_block block-3\" style=\"height:20px;line-height:20px;font-size:1px;\">&#8202;</div>\n" +
                "                                    <table class=\"paragraph_block block-4\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:15px;padding-left:35px;padding-right:30px;padding-top:10px;\">\n" +
                "                                                <div style=\"color:#fefefe;font-family:'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif;font-size:16px;letter-spacing:0px;line-height:150%;text-align:justify;mso-line-height-alt:24px;\">\n" +
                "                                                    <p style=\"margin: 0; word-break: break-word;\">El dueño de: "+petName+" ha solicitado dar de baja a la mascota, atiende a su petición lo más pronto posibe.</p>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }
    public static String requestChangesOrAprove(String message){
        return "<!DOCTYPE html>\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Baloo&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <style>\n" +
                "        * {\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        #MessageViewBody a {\n" +
                "            color: inherit;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            line-height: inherit\n" +
                "        }\n" +
                "\n" +
                "        .desktop_hide,\n" +
                "        .desktop_hide table {\n" +
                "            mso-hide: all;\n" +
                "            display: none;\n" +
                "            max-height: 0px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        .image_block img+div {\n" +
                "            display: none;\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width:660px) {\n" +
                "            .image_block div.fullWidth {\n" +
                "                max-width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                display: none;\n" +
                "            }\n" +
                "\n" +
                "            .row-content {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .stack .column {\n" +
                "                width: 100%;\n" +
                "                display: block;\n" +
                "            }\n" +
                "\n" +
                "            .mobile_hide {\n" +
                "                min-height: 0;\n" +
                "                max-height: 0;\n" +
                "                max-width: 0;\n" +
                "                overflow: hidden;\n" +
                "                font-size: 0px;\n" +
                "            }\n" +
                "\n" +
                "            .desktop_hide,\n" +
                "            .desktop_hide table {\n" +
                "                display: table !important;\n" +
                "                max-height: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        /* Estilos con la fuente Baloo */\n" +
                "        body {\n" +
                "            font-family: 'Baloo', sans-serif !important;\n" +
                "        }\n" +
                "\n" +
                "        .button_block a {\n" +
                "            font-family: 'Baloo', sans-serif !important;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #f0fffa; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f0fffa;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #118a95;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #118a95; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 60px; padding-left: 30px; padding-right: 30px; padding-top: 30px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                                    <table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div style=\"max-width: 116px;\"><a href=\"http://www.example.com/\" target=\"_blank\" style=\"outline:none\" tabindex=\"-1\"><img src=\"https://d15k2d11r6t6rl.cloudfront.net/pub/bfra/y8he417t/og7/l0x/9x0/logo%20blanco.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"116\" alt=\"logo\" title=\"logo\"></a></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"paragraph_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-top:40px;\">\n" +
                "                                                <div style=\"color:#ffffff;direction:ltr;font-family:'Baloo', sans-serif;font-size:16px;font-weight:400;letter-spacing:0px;line-height:150%;text-align:center;mso-line-height-alt:24px;\">\n" +
                "                                                    <p style=\"margin: 0;\">"+message+"</p>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"image_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-top:20px;width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                                                <div class=\"alignment\" align=\"center\" style=\"line-height:10px\">\n" +
                "                                                    <div class=\"fullWidth\" style=\"max-width: 464px;\"><a href=\"http://www.example.com/\" target=\"_blank\" style=\"outline:none\" tabindex=\"-1\"><img src=\"https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6696/pet-store-main1__1_.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"464\" alt=\"national pet day\" title=\"national pet day\"></a></div>\n" +
                "                                                </div>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }
}