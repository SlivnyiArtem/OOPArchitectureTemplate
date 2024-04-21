import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.BotBehaviour;

public class BotBehaviourTest {
    @Test
    public void makeAnswerStandardMessage(){
        BotBehaviour botBehaviour = new BotBehaviour();
        var actualAnswer = botBehaviour.makeAnswer("1", "Hello");
        var expectedAnswer = "\"Ваше сообщение: 'привет'\"";
        Assertions.assertEquals(actualAnswer, expectedAnswer);
    }
}
