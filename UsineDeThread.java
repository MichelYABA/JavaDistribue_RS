import java.util.concurrent.ThreadFactory;

public class UsineDeThread implements ThreadFactory{

    private ThreadGroup monGroupe;

    UsineDeThread(ThreadGroup groupe){
        this.monGroupe = groupe;
    }

    public Thread newThread(Runnable r){
        return new Thread(monGroupe, r);
    }

}