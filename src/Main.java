public class Main {

    public static void main(String[] args) {
        //элементы ребра записываются в порядке: (начало ребра,конец ребра)
        RibElement element_1 = new RibElement(1, (byte) 1);
        RibElement element_2 = new RibElement(2, (byte) 1);
        RibElement element_3 = new RibElement(3, (byte) 1);
        RibElement element_4 = new RibElement(4, (byte) 1);
        RibElement element_5 = new RibElement(5, (byte) 0);
        RibElement element_6 = new RibElement(6, (byte) 0);
        RibElement element_7 = new RibElement(7, (byte) 1);
        RibElement element_8 = new RibElement(8, (byte) 0);
        RibElement element_9 = new RibElement(9, (byte) 0);

        Graph graf = new Graph(
                new Rib (element_1, element_6, "g",(byte) 1),
                new Rib (element_3, element_1, "w",(byte) 0),
                new Rib (element_3, element_5, "w",(byte) 1),
                new Rib (element_2, element_3, "g",(byte) 1),
                new Rib (element_4, element_3, "r",(byte) 1),
                new Rib (element_2, element_6, "t",(byte) 1),
                new Rib (element_7, element_2, "r",(byte) 1),
                new Rib (element_7, element_8, "r",(byte) 1),
                new Rib (element_7, element_9, "w",(byte) 1));

                new Rib (element_9, element_6, "w",(byte) 0);
        System.out.println("\n" + "can_write(" + element_3 + "," + element_9 + ",{w}) = " + Can_Write.can_write(graf,element_3,element_9));

    }
}
