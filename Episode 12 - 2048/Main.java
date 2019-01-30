class Main {

    public static void main(String[] args) {
        GameManager gm = new GameManager();
        gm.readFieldState();
        gm.playerAction();
        System.out.println(gm.toString());
    }

}
