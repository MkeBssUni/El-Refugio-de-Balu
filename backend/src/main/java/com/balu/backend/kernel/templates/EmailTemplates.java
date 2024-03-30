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

    public static String InterestedAdoptingTemplate(String namePet){
        return null;
    }
}