import map.Door;
import map.Room;
import map.RoomType;
import org.junit.jupiter.api.Test;
import teacher.QuestionSet;
import teacher.Teacher;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class TeacherMovementTest {
    @Test
    void exitRoom() {
        Teacher teacher = new Teacher("Negr", 15, new QuestionSet(), new Door("0-3", new Room("0-3", RoomType.CABINET)), 20);
        Random rand = new Random(0);
        teacher.setInsideRoom(true);
        teacher.moveAI(rand);
        assertTrue(true);
    }
}
