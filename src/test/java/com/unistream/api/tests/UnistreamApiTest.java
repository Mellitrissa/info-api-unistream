package com.unistream.api.tests;

import com.unistream.api.model.Fee;
import com.unistream.api.model.RequestCommissionCalculation;
import com.unistream.api.model.ResponseCommissionCalculation;
import com.unistream.api.spec.Specification;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class UnistreamApiTest {
    @Test
    @DisplayName("Расчёт комиссии за перевод из банка 10400 в Армению на 1000 рублей")
    public void testCalculateTransferFee() {
        RequestCommissionCalculation request = new RequestCommissionCalculation(10400, "ARM",
                "RUB", "RUB", 1000, "Transfer");
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec(200));
        ResponseCommissionCalculation response = given()
                .filter(new AllureRestAssured())
                .body(request)
                .post("api/v1/transfer/calculate")
                .then()
                .log().all()
                .extract().as(ResponseCommissionCalculation.class);

        Assertions.assertThat(response.getFees()).isNotEmpty();
        Fee fee = response.getFees().get(0);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(fee.getName())
                    .isEqualTo("Перевод в страну Армения");
            softly.assertThat(fee.getAcceptedAmount())
                    .isEqualTo(1100);
            softly.assertThat(fee.getAcceptedCurrency())
                    .isEqualTo("RUB");
            softly.assertThat(fee.getAcceptedTotalFee())
                    .isEqualTo(100);
        });


    }

    @Test
    @DisplayName("Поиск адреса ближайшего отделения банка от г. Балашиха, Дмитриева 34 ")
    public void testSearchNearestOffice() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec(200));
        Response response = given()
                .queryParam("location", "55.834846, 37.934417")
                .queryParam("filter.country", "rus")
                .queryParam("filter.region", 674)
                .when()
                .get("api/v1/poses/search");
        JsonPath jsonPath = response.jsonPath();
        String actualAddress = jsonPath.getString("items[0].address");
        Assertions.assertThat(actualAddress)
                .isEqualTo("Московская область, Балашиха, Советская, 13");
    }

}
