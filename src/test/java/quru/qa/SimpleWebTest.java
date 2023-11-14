package quru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTest {

    @BeforeEach
    void setup () {
        open("http://ya.ru");
    }

    @ValueSource(strings = {
            "Selenide", "Allure"
    })
    @ParameterizedTest(name = "В поисковой выдаче яндекса должно отображаться 10 результатов по запросу {0}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void searchResultsShouldBeGreaterThan10 (String testData) {
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").shouldHave(sizeGreaterThanOrEqual(10));
    }


    @CsvSource(value = {
            "Selenide,                      : лаконичные и стабильные UI тесты на Java",
            "allure framework,              · GitHub"

    })
    @ParameterizedTest(name = "В первом результате выдачи для {0} должен отображаться текст {1} ")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void firstSearchResultsShouldContainExpectedText (String testData, String expectedText) {
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").first().shouldHave(Condition.text(expectedText));
    }
}
