package com.balu.backend.kernel.templates;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailTemplates {
    public static String mailTemplate(String message){
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
                "                                                    <p style=\"margin: 0; word-break: break-word;\">"+message+".</p>\n" +
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