public enum Status {

    ASIGNADO("Asignado"),
    RESUELTO("Resuelto");



    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    Status(String status){
        this.status = status;

    }

}
