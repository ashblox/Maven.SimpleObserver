package interfaces;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void alert();
}
