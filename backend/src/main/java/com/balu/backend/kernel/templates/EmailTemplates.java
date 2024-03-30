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
                "      font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif !important;\n" +
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
                "                  <table class=\"button_block block-4\" width=\"100%\" border=\"0\" cellpadding=\"55\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div class=\"alignment\" align=\"center\">\n" +
                "                          <!--[if mso]>\n" +
                "                          <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"www.example.com\" style=\"height:48px;width:222px;v-text-anchor:middle;\" arcsize=\"114%\" strokeweight=\"0.75pt\" strokecolor=\"#FFDDA1\" fillcolor=\"#ff9900\">\n" +
                "                            <w:anchorlock/>\n" +
                "                            <v:textbox inset=\"0px,0px,0px,0px\">\n" +
                "                              <center style=\"color:#f5f3fc; font-family:Arial, sans-serif; font-size:16px\">\n" +
                "                          <![endif]-->\n" +
                "                          <a href=\"www.example.com\" target=\"_blank\" style=\"text-decoration:none;display:inline-block;color:#f5f3fc;background-color:#ff9900;border-radius:50px;width:auto;border-top:1px solid #FFDDA1;font-weight:400;border-right:1px solid #FFDDA1;border-bottom:1px solid #FFDDA1;border-left:1px solid #FFDDA1;padding-top:5px;padding-bottom:5px;font-family:'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\">\n" +
                "                            <span style=\"padding-left:35px;padding-right:35px;font-size:16px;display:inline-block;letter-spacing:normal;\">\n" +
                "                            </span>\n" +
                "                          </a>\n" +
                "                          <!--[if mso]></center></v:textbox></v:roundrect><![endif]-->\n" +
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
                "      <table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 680px; margin: 0 auto;\" width=\"680\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">\n" +
                "                  <table class=\"icons_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">\n" +
                "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                          <tr>\n" +
                "                            <td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\">\n" +
                "                              <!--[if vml]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </table>\n" +
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
                "                                                <h1 style=\"margin: 0; color: #f0f0f0; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 40px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 48px;\"><span class=\"tinyMce-placeholder\">¡Lo Sentimos!</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"heading_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\" style=\"padding-bottom:5px;padding-left:10px;padding-right:10px;text-align:center;width:100%;\">\n" +
                "                                                <h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 33px; font-weight: normal; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 39.6px;\"><span class=\"tinyMce-placeholder\">&nbsp;Su solicitud de adopción de "+namePet+" ha sido finalizada</span></h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                    <table class=\"button_block block-4\" width=\"100%\" border=\"0\" cellpadding=\"55\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">\n" +
                "                                        <tr>\n" +
                "                                            <td class=\"pad\">\n" +
                "                                                <div class=\"alignment\" align=\"center\"><!--[if mso]>\n" +
                "                                                    <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"www.example.com\" style=\"height:48px;width:222px;v-text-anchor:middle;\" arcsize=\"114%\" strokeweight=\"0.75pt\" strokecolor=\"#FFDDA1\" fillcolor=\"#ff9900\">\n" +
                "                                                        <w:anchorlock/>\n" +
                "                                                        <v:textbox inset=\"0px,0px,0px,0px\">\n" +
                "                                                            <center style=\"color:#f5f3fc; font-family:'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size:16px\">\n" +
                "                                                    <![endif]--><a href=\"www.example.com\" target=\"_blank\" style=\"text-decoration:none;display:inline-block;color:#f5f3fc;background-color:#ff9900;border-radius:50px;width:auto;border-top:1px solid #FFDDA1;font-weight:400;border-right:1px solid #FFDDA1;border-bottom:1px solid #FFDDA1;border-left:1px solid #FFDDA1;padding-top:5px;padding-bottom:5px;font-family:'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif;font-size:16px;text-align:center;mso-border-alt:none;word-break:keep-all;\"><span style=\"padding-left:35px;padding-right:35px;font-size:16px;display:inline-block;letter-spacing:normal;\"><span style=\"word-break: break-word; line-height: 32px;\">Ver más información</span></span></a><!--[if mso]></center></v:textbox></v:roundrect><![endif]--></div>\n" +
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
                "                                                <h1 style=\"margin: 0; color: #f3f2f7; direction: ltr; font-family: 'Baloo', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 29px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 43.5px;\"><span class=\"tinyMce-placeholder\">Tienes "+countRequest+"solicitudes de adopción de "+namePet+" pendientes de revisión .</span></h1>\n" +
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
}