package teacher;

import map.Door;

public class TeacherFactory {

    public static Teacher create(TeacherType type, Door start) {
        return new Teacher(type.name(), type.baseAI, type.questionSet, start, type.timeLimit);
    }
}