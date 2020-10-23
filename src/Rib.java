class Rib {

    private byte type; //  0 - мнимое, 1 - реальное
    private RibElement x,y;// x - начало, y - конец;
    private String a; // a - тип элементарной операции;

    Rib(RibElement x, RibElement y, String a, byte type) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.type = type;
    }

    /**
     * возвращает первый элемент ребра
     *
     * @return RibElement - первый элемент.
     */
    RibElement getX() {
        return x;
    }

    /**
     * возвращает второй элемент ребра
     *
     * @return RibElement - второй элемент.
     */
    RibElement getY() {
        return y;
    }

    /**
     * возвращает элементарную операцию ребра
     *
     * @return String - название операции.
     */
    String getA() {
        return a;
    }

    /**
     * возвращает тип ребра
     *
     * @return byte - тип ребра.
     */
    byte getType () {
        return type;
    }
}
