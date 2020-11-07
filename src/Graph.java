import java.util.*;

class Graph {
    private static List<Rib> ribList = new ArrayList<>();

    Graph(Rib... ribs) {
        ribList.addAll(Arrays.asList(ribs));
    }

    /**
     * Возвращает все ребра, которые выходят и входят в x
     *
     * @param x                  - исследуемый элемент;
     * @return List <RibElement> - список таких ребер.
     */
    List <RibElement> getAllElementRibs (RibElement x) {
        List <RibElement> listElements = new ArrayList<>();

        for (Rib r : ribList) {
            if (r.getX().equals(x))
                listElements.add(r.getY());
            else if (r.getY().equals(x))
                listElements.add(r.getX());
        }
        return listElements;
    }

    /**
     * Возвращает список всех объектов в графе
     *
     * @return Set <RibElement> - список всех объектов в графе.
     */
    Set <RibElement> getAllElementObject () {
        Set<RibElement> listElements = new HashSet<>();
        for (Rib r : ribList) {
            if (r.getX().getType() == 0)
                listElements.add(r.getX());
            else if (r.getY().getType() == 0)
                listElements.add(r.getY());
        }
        return listElements;
    }


    /**
     * Проверка существования ребра (elementIndex1,elementIndex2,a)
     *
     * @param x        - x-элемент ребра;
     * @param y        - y-элемент ребра;
     * @param a        - исследуемая операция ребра;
     * @return boolean - true: такое ребро существует, false: такого ребра не существует.
     */
    boolean doesRibExist (RibElement x,RibElement y,String a) {

        for (Rib r : ribList) {
            if (r.getA().equals(a) && r.getX().equals(x) && r.getY().equals(y))
                return true;
        }
        return false;
    }

    /**
     * Проверка существования мнимого ребра с
     * операцией {w} между elementIndex1 и elementIndex2
     *
     * @param x        - x-элемент ребра;
     * @param y        - y-элемент ребра;
     * @return boolean - true: такое ребро существует, false: такого ребра не существует.
     */
    boolean doesVirtualRibExist(RibElement x, RibElement y) {
        for (Rib r : ribList) {
            if (r.getA().equals("w") && r.getX().equals(x) && r.getY().equals(y) && r.getType() == 0)
                return true;
        }
        return false;
    }
}
