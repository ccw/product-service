package com.digitalriver.catalog.api.controller;

import com.digitalriver.catalog.api.service.ProductService;
import groovy.json.JsonBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pd")
public class ProductController {

    @Resource
    protected ProductService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{locale}/{productID}/", produces = "application/json")
    public String get(@PathVariable String productID, @PathVariable String locale) {
        return new JsonBuilder(service.get(productID, locale)).toPrettyString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
    public String test() {
        return "{\n" +
                "    \"productInfo\": {\n" +
                "        \"product\": {\n" +
                "            \"productID\": 288660400,\n" +
                "            \"purchasable\": true,\n" +
                "            \"includeVariations\": true,\n" +
                "            \"name\": \"Rosetta Course French - Download\",\n" +
                "            \"stockStatus\": {\n" +
                "                \"available\": null,\n" +
                "                \"status\": \"Transferência de arquivos\",\n" +
                "                \"allowsBackorders\": true,\n" +
                "                \"isPhysical\": false\n" +
                "            },\n" +
                "            \"shortDescription\": \"<h2 class=\\\"dr_productName\\\">Rosetta Course</h2><style=\\\"font-size: 18px; line-height: 20px;\\\"><strong> Download </strong></style><br> <div class=\\\"dr_WhatYoullLearnDL\\\"> <a class=\\\"learn\\\" href=\\\"/store/rstbr/custom/pbPage.WhatYoullLearnDL\\\">O que você aprenderá &#8250;</a> </div><br>O que faz parte do<Strong> Rosetta Course?</Strong><br> <ul style=\\\"padding: 5px 10px 10px 37px;\\\"> <li style=\\\"font-size: 13px; line-height: 20px;\\\"><strong> SOFTWARE INTERATIVO — </strong> Nosso curso premiado.<br> <li style=\\\"font-size: 13px; line-height: 20px;\\\"><strong> TECNOLOGIA DE RECONHECIMENTO DE FALA PATENTEADA — </strong> Nossa tecnologia de reconhecimento de fala identifica as palavras e sua pronúncia retornando imediatamente com a validação. <li style=\\\"font-size: 13px; line-height: 20px;\\\"><strong>ACESSO PARA TODA A FAMÍLIA — </strong> O software pode ser instalado em até 2 computadores e utilizado por até 5 membros da família. <br> </li></ul>\",\n" +
                "            \"longDescription\": \"<div id=\\\"longDescriptionText\\\"> <div class=\\\"dr_leftColumn\\\"> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/interactive2.png\\\" alt=\\\"Software interativo\\\"/> <p><strong>Software interativo</strong>Nossa última versão premiada ensina você a falar, ler, escrever e pensar no novo idioma.</p> </div> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/games2.png\\\" alt=\\\"Jogos e Comunidade\\\"/> <p><strong>Jogos e Comunidade*†</strong>Jogos voltados para o aprimoramento do idioma fazem com que você ganhe proficiência no mundo real.</p> </div> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/mobile2.png\\\" alt=\\\"On the Go\\\"/> <p><strong>A qualquer hora com nossos aplicativos móveis Aplicativos móveis†</strong>Melhore seu aprendizado com nossos aplicativos para iPad ®, iPhone ® e Android ™. Saiba mais</p> </div> </div> <div class=\\\"dr_rightColumn\\\"> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/conversation2.png\\\" alt=\\\"Sessões de conversação ao vivo\\\"/> <p><strong>Sessões de conversação ao vivo*†</strong>Sessões on-line com falantes nativos o ajudam a refinar suas habilidades de conversação.</p> </div> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/home2.png\\\" alt=\\\"Em casa\\\"/> <p><strong>Em casa no seu PC ou MAC</strong> Acesso de qualquer Mac ou PC com conexão internet, em qualquer navegador.</p> </div> </div> <p id=\\\"dr_disclaimer\\\">*Deve ter 13 anos ou mais para utilizar esta ferramenta.<br />†Incluído 3 meses de acesso grátis em compras para um usuário.</p> </div>\",\n" +
                "            \"product.variation\": false,\n" +
                "            \"product.allVariations.size\": 1,\n" +
                "            \"price\": {\n" +
                "                \"siteOptimizerTracking\": null,\n" +
                "                \"taxIncludedInPrice\": true,\n" +
                "                \"discounted\": false,\n" +
                "                \"regularPrice\": \"R$ 349,00\",\n" +
                "                \"actualPrice\": \"R$ 349,00\",\n" +
                "                \"unitPrice\": \"R$ 349,00\",\n" +
                "                \"unitPriceWithDiscount\": \"R$ 699,00\",\n" +
                "                \"youSave\": \"R$ 0,00\",\n" +
                "                \"visualDiscount\": null,\n" +
                "                \"installments\": {\n" +
                "                    \"installmentPrice\": [\n" +
                "                        {\n" +
                "                            \"numberOfInstallments\": 1,\n" +
                "                            \"#text\": \"R$ 349,00\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"numberOfInstallments\": 3,\n" +
                "                            \"#text\": \"R$ 116,33\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"numberOfInstallments\": 6,\n" +
                "                            \"#text\": \"R$ 58,17\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"numberOfInstallments\": 10,\n" +
                "                            \"#text\": \"R$ 34,90\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"numberOfInstallments\": 12,\n" +
                "                            \"#text\": \"R$ 29,08\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"installmentMessage\": \"<span class=\\\"dr_installmentMaxTimePrefix\\\">12x de sem juros</span> <span class=\\\"dr_installmentPriceMax\\\">R$ 29,08</span>\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"ProductType\": \"Download\",\n" +
                "            \"productLanguage\": \"Francês\",\n" +
                "            \"cartTagline\": null,\n" +
                "            \"landingPageDisplayName\": null,\n" +
                "            \"hideCatPulldown\": false,\n" +
                "            \"catalog\": {\n" +
                "                \"isBaseProduct\": true,\n" +
                "                \"isIndividualProduct\": false,\n" +
                "                \"variation\": {\n" +
                "                    \"varProductID\": 288660600,\n" +
                "                    \"isOrderableVariation\": true,\n" +
                "                    \"isAutomatic\": \"\",\n" +
                "                    \"variationDefiningValues\": {\n" +
                "                        \"attribute\": {\n" +
                "                            \"attrKey\": \"productLevels\",\n" +
                "                            \"attrValue\": \"Completo\",\n" +
                "                            \"attrDisplay\": \"Completo\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"product\": {\n" +
                "                        \"productID\": 288660600,\n" +
                "                        \"purchasable\": true,\n" +
                "                        \"includeVariations\": false,\n" +
                "                        \"name\": \"Rosetta Course French - Download\",\n" +
                "                        \"stockStatus\": {\n" +
                "                            \"available\": null,\n" +
                "                            \"status\": \"Transferência de arquivos\",\n" +
                "                            \"allowsBackorders\": true,\n" +
                "                            \"isPhysical\": false\n" +
                "                        },\n" +
                "                        \"shortDescription\": null,\n" +
                "                        \"longDescription\": \"<div id=\\\"longDescriptionText\\\"> <div class=\\\"dr_leftColumn\\\"> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/interactive2.png\\\" alt=\\\"Software interativo\\\"/> <p><strong>Software interativo</strong>Nossa última versão premiada ensina você a falar, ler, escrever e pensar no novo idioma.</p> </div> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/games2.png\\\" alt=\\\"Jogos e Comunidade\\\"/> <p><strong>Jogos e Comunidade*†</strong>Jogos voltados para o aprimoramento do idioma fazem com que você ganhe proficiência no mundo real.</p> </div> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/mobile2.png\\\" alt=\\\"On the Go\\\"/> <p><strong>A qualquer hora com nossos aplicativos móveis Aplicativos móveis†</strong>Melhore seu aprendizado com nossos aplicativos para iPad ®, iPhone ® e Android ™. Saiba mais</p> </div> </div> <div class=\\\"dr_rightColumn\\\"> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/conversation2.png\\\" alt=\\\"Sessões de conversação ao vivo\\\"/> <p><strong>Sessões de conversação ao vivo*†</strong>Sessões on-line com falantes nativos o ajudam a refinar suas habilidades de conversação.</p> </div> <div class=\\\"dr_listItem\\\"> <img src=\\\"//drh.img.digitalriver.com/DRHM/Storefront/Site/rstbr/cm/images/Checkout/home2.png\\\" alt=\\\"Em casa\\\"/> <p><strong>Em casa no seu PC ou MAC</strong> Acesso de qualquer Mac ou PC com conexão internet, em qualquer navegador.</p> </div> </div> <p id=\\\"dr_disclaimer\\\">*Deve ter 13 anos ou mais para utilizar esta ferramenta.<br />†Incluído 3 meses de acesso grátis em compras para um usuário.</p> </div>\",\n" +
                "                        \"product.variation\": true,\n" +
                "                        \"product.allVariations.size\": 0,\n" +
                "                        \"price\": {\n" +
                "                            \"siteOptimizerTracking\": null,\n" +
                "                            \"taxIncludedInPrice\": true,\n" +
                "                            \"discounted\": true,\n" +
                "                            \"regularPrice\": \"R$ 799,00\",\n" +
                "                            \"actualPrice\": \"R$ 699,00\",\n" +
                "                            \"unitPrice\": \"R$ 799,00\",\n" +
                "                            \"unitPriceWithDiscount\": \"R$ 699,00\",\n" +
                "                            \"youSave\": \"R$ 100,00\",\n" +
                "                            \"visualDiscount\": \"R$ 100,00\",\n" +
                "                            \"installments\": {\n" +
                "                                \"installmentPrice\": [\n" +
                "                                    {\n" +
                "                                        \"numberOfInstallments\": 1,\n" +
                "                                        \"#text\": \"R$ 699,00\"\n" +
                "                                    },\n" +
                "                                    {\n" +
                "                                        \"numberOfInstallments\": 3,\n" +
                "                                        \"#text\": \"R$ 233,00\"\n" +
                "                                    },\n" +
                "                                    {\n" +
                "                                        \"numberOfInstallments\": 6,\n" +
                "                                        \"#text\": \"R$ 116,50\"\n" +
                "                                    },\n" +
                "                                    {\n" +
                "                                        \"numberOfInstallments\": 10,\n" +
                "                                        \"#text\": \"R$ 69,90\"\n" +
                "                                    },\n" +
                "                                    {\n" +
                "                                        \"numberOfInstallments\": 12,\n" +
                "                                        \"#text\": \"R$ 58,25\"\n" +
                "                                    }\n" +
                "                                ],\n" +
                "                                \"installmentMessage\": \"<span class=\\\"dr_installmentMaxTimePrefix\\\">12x de sem juros</span> <span class=\\\"dr_installmentPriceMax\\\">R$ 58,25</span>\"\n" +
                "                            }\n" +
                "                        },\n" +
                "                        \"ProductType\": \"Download\",\n" +
                "                        \"productLanguage\": \"Francês\",\n" +
                "                        \"cartTagline\": \"Curso Completo\",\n" +
                "                        \"landingPageDisplayName\": null,\n" +
                "                        \"hideCatPulldown\": false\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

}
