import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;

public class OrderingCardDelivery {

    public String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    @Test
    public void shouldSetForm() {

        String planningDate = generateDate(7);

        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x600";
        Configuration.headless = true;

        open("http://localhost:9999/");
        $x("//input[@type='text']").val("Ростов-на-Дону");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(SHIFT, HOME), BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $x("//input[@name='name']").val("Суханов Артем");
        $x("//input[@name='phone']").val("+79612333444");
        $x("//label[@data-test-id='agreement']").click();
        $(withText("Забронировать")).click();

        $x("//*[contains(text(),'Успешно!')]").should(appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));

    }

}
