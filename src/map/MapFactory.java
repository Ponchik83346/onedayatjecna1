package map;

import gameData.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class MapFactory {
    private MapFactory() {}

    public static Map load() {
        MapData data = JsonLoader.load("/map.json", MapData.class);

        HashMap<String, Room> roomsById = new HashMap<>();
        HashMap<String, Door> doorsByRoomId = new HashMap<>();
        List<Floor> floors = new ArrayList<>();
        for (FloorData fd : data.getFloors()) {
            List<Door> doors = new ArrayList<>();
            for (RoomData rd : fd.getRooms()) {
                Room room = new Room(rd.getId(), rd.getType());
                Door door = new Door(rd.getId(), room);
                roomsById.put(rd.getId(), room);
                doorsByRoomId.put(rd.getId(), door);
                doors.add(door);
            }
            floors.add(new Floor(fd.getFloor(), doors));
        }
        for (int i = 0; i < floors.size(); i++) {
            Floor floor = floors.get(i);
            List<Door> doors = floor.getDoors();
            List<RoomData> roomDatas = data.getFloors().get(i).getRooms();

            for (int j = 0; j < doors.size(); j++) {
                Door door = doors.get(j);
                RoomData rd = roomDatas.get(j);
                if (j > 0) door.setLeft(doors.get(j - 1));
                if (j < doors.size() - 1) door.setRight(doors.get(j + 1));
                if (rd.getType() == RoomType.STAIRS) {
                    if (rd.getDownStairsId() != null) door.setDownDoor(doorsByRoomId.get(rd.getDownStairsId()));
                    if (rd.getUpStairsId() != null) door.setUpDoor(doorsByRoomId.get(rd.getUpStairsId()));
                }
            }
        }

        return new Map(floors);
    }
}
