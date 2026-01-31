package map;

import gameData.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class MapFactory {
    private MapFactory() {}

    public static Map load() {
        MapData data = JsonLoader.load("/map.json", MapData.class);
        List<Floor> floors = new ArrayList<>();
        HashMap<String, Room> roomsById = new HashMap<>();

        for (FloorData floorData : data.getFloors()) {
            List<Door> doors = new ArrayList<>();
            for (RoomData rd : floorData.getRooms()) {
                Room room = new Room(rd.getId(), rd.getType());
                roomsById.put(rd.getId(), room);

                Door door = new Door(rd.getId(), room);
                doors.add(door);
            }
            floors.add(new Floor(floorData.getFloor(), doors));
        }
            for (int i = 0; i < floors.size(); i++) {
                Floor floor = floors.get(i);
                FloorData fd = data.getFloors().get(i);
                List<Door> doors = floor.getDoors();
                for (int j = 0; j < doors.size(); j++) {
                    if (i > 0){
                        doors.get(i).setLeft(doors.get(i - 1));
                    }
                    if (i < doors.size() - 1){
                        doors.get(i).setRight(doors.get(i + 1));
                    }
                }

                Room room = doors.get(i).getConnectedRoom();
                RoomData rd = fd.getRooms().get(i);

                if (room.getType() == RoomType.STAIRS) {
                    if (rd.getDownStairsId() != null &&
                            !roomsById.containsKey(rd.getDownStairsId())) {
                        throw new IllegalStateException("Missing room: " + rd.getDownStairsId());
                    }

                    if (rd.getUpStairsId() != null &&
                            !roomsById.containsKey(rd.getUpStairsId())) {
                        throw new IllegalStateException("Missing room: " + rd.getUpStairsId());
                    }
                }
            }
        return new Map(floors);
    }
}
