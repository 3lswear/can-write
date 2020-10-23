import java.util.*;

class Can_Write {

    private static Set <RibElement> notNeededRibs = new LinkedHashSet<>();
    private static ArrayList <RibElement> currentElements = new ArrayList<>();
    private static Graph currentGraph;

    static boolean can_write(Graph graph,RibElement x,RibElement y) {
        currentGraph = graph;
        return (rule_1(x,y) || rule_2(x,y));
    }

    // проверяем, условие (1) : между x и y уже есть мнимое ребро с операцией {w}.
    private static boolean rule_1(RibElement x,RibElement y) {
       return (currentGraph.doVirtualRibExists(x,y));
    }

    private static void printElements () {
        System.out.print("текущие вершины: ");
        for (RibElement element : currentElements)
            System.out.print(element + ", ");

        System.out.print("\nотработанные вершины: ");
        for (RibElement el : notNeededRibs)
            System.out.print(el + ", ");
    }

    //проверяем условие (2) : i=1,...,m-1 выполняется одно из условий:
    private static boolean rule_2(RibElement x,RibElement y) {
        currentElements.add(x);

        while (!(currentElements.isEmpty())) {
            System.out.println("===============================");
            printElements();
            System.out.println("\n-------------------------------------\nисследуемая вершина - " + currentElements.get(0));

            // список всех ребер (входящих и выходящих из текущего элемента)
            List <RibElement> list = currentGraph.getAllElementRibs(currentElements.get(0));

            if (list.isEmpty()) {
                clearElementsList(currentElements.get(0));
            } else {
                System.out.print("инцедентные вершины: ");
                for (RibElement element : list)
                    System.out.print(element + ", ");

                for (RibElement element : list) {
                    System.out.println("\nelement - " + element);

                    if (!notNeededRibs.contains(element)) {
                        if (checkRules(currentElements.get(0),element)) {
                            if (element.equals(y)) {
                                clearElementsList(currentElements.get(0));
                                printElements();
                                return true;
                            }
                        }
                        else System.out.println(" -> вершина не подходит");
                    }
                    else System.out.println(" -> вершина уже отработала");
                }
                clearElementsList(currentElements.get(0));
            }
        }
        return false;
    }

    private static boolean checkRules (RibElement o1, RibElement o2) {
        //проверяем условие (2.1) : o_1 это субъект и:
        if (isSubject(o1)) {
            //проверяем условие (2.1.2) : между элементом o_1 и элементом o_2 ребро с операцией {w}
            if (currentGraph.doesRibExist(o1,o2, "w")) {
                System.out.println("rule_212 for: " + o1 + " and " + o2);
                currentElements.add(o2);
                return true;
            }
        }
        //проверяем условие (2.2) : o_2 это субъект и:
        if (isSubject(o2)) {
            //проверяем условие (2.2.2) : между элементом o_2 и элементом o_1 ребро с операцией {r}
            if (currentGraph.doesRibExist(o2,o1, "r")) {
                System.out.println("rule_222 for: " + o1 + " and " + o2);
                currentElements.add(o2);
                return true;
            }
        }
        // проверяем условие (2.3) : o1 и o2 это субъекты и:
        if (isSubject(o1) && isSubject(o2)) {
            // проверяем условие (2.3.1) : между o_1 и o_2 есть ребро с опреацией "a = {t,g}"
            if (currentGraph.doesRibExist(o1,o2,"t") ||
                    currentGraph.doesRibExist(o1,o2,"g")) {
                System.out.println("rule_231 for: " + o1 + " and " + o2);
                currentElements.add(o2);
                return true;
            // проверяем условие (2.3.2) : между o_2 и o_1 есть ребро с операцией "a = {t,g}"
            } else if (currentGraph.doesRibExist(o2,o1, "t") ||
                       currentGraph.doesRibExist(o2,o2, "g")) {
                System.out.println("rule_232 for: " + o1 + " and " + o2);
                currentElements.add(o2);
                return true;
            // проверяем условие (2.3.3) : существует элемент o`,который может быть как объектом,
            //                             так и субъектом, такой что:
            } else if (isObjectExists()) {
                Set<RibElement> listObjects = currentGraph.getAllElementObject();
                for (RibElement object : listObjects) {
                    // проверяем условие (2.3.3.1) : между o_1 и o` есть ребро с опреацией {t} И
                    //                               между o_2 и o` есть ребро с операцией {g}
                    if (currentGraph.doesRibExist(o1,object, "t") ||
                            currentGraph.doesRibExist(o2,object, "g")) {
                        System.out  .println("rule_2331 for: " + o1 + " and " + o2);
                        currentElements.add(o2);
                        return true;
                    //проверяем условие (2.3.2.2) : между o_1 и o` есть ребро с опреацией {g} И
                    //                              между o_2 и o` есть ребро с операцией {t}
                    } else if (currentGraph.doesRibExist(o1,object, "g") ||
                               currentGraph.doesRibExist(o2,object, "t")) {
                        System.out.println("rule_2332 for: " + o1 + " and " + o2);
                        currentElements.add(o2);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Убираем из стека отработанную вершину и
     *  записываем его в список отработанных вершин
     *
     * @param element - отработанная вершина.
     */
    private static void clearElementsList(RibElement element) {
        notNeededRibs.add(element);
        currentElements.remove(element);
    }

    /**
     * Проверяется, что текущий элемент является субъектом
     *
     * @param element  - текущий элемент;
     * @return boolean - true: если является субъектом, false: если не является субъектом.
     */
    private static boolean isSubject (RibElement element) {
        return  (element.getType() == 1);
    }

    /**
     * Проверяется, есть ли в текущем графе объекты
     *
     * @return boolean - true: если объекты есть, false: если объектов нет.
     */
    private static boolean isObjectExists () {
        return !currentGraph.getAllElementObject().isEmpty();
    }
}
