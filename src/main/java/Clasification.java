public enum Clasification {
    CRITICO("Critico"),
    NORMAL("Normal"),
    MENOR("Menor");

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    Clasification(String status){
        this.status = status;

    }

}
