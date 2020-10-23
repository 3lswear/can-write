class RibElement {

    private int id; // id - должно начинаться с 1;
    private int type; // 0 - это объект, если 1 - субъект;

    RibElement(int id, byte type) {
        this.id = id;
        this.type = type;
    }

    /**
     * возвращает тип элемента
     *
     * @return int - тип элемента.
     */
    int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{" +
                "ID_" + id +
                '}';
    }
}
