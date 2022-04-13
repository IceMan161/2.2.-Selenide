import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderingCardDelivery {

    @Test
    public void shouldSetForm() {

        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x600";

        open("http://localhost:9999/");
        $x("//input[@type='text']").val("Ростов-на-Дону");
        //       $x("//input[@type='tel']").clear();
        //       $x("//input[@type='tel']").val("19.04.2022");
        $x("//input[@name='name']").val("Суханов Артем");
        $x("//input[@name='phone']").val("+79612333444");
        $x("//label[@data-test-id='agreement']").click();
        $(withText("Забронировать")).click();

        $x("//*[contains(text(),'Успешно!')]").should(appear, Duration.ofSeconds(15));

    }

}
