package A1;

//import com.sun.istack.internal.NotNull;
import javax.validation.constraints.NotNull;

/**
 * Created by Meghana on 2/27/2015.
 */

public class Poll_noRes
{
    private  String id;
    @NotNull
    private  String question;
    @NotNull
    private  String started_at;
    @NotNull
    private  String expired_at;
    @NotNull
    private  String[] choice;

    public Poll_noRes(String id, String question, String started_at, String expired_at, String[] choice) {
        this.id = id;
        this.question = question;
        this.started_at = started_at;
        this.expired_at = expired_at;
        this.choice = choice;
    }
    public Poll_noRes(Poll_noRes pol) {
        this.id = pol.getId();
        this.question = pol.getQuestion();
        this.started_at = pol.getStarted_at();
        this.expired_at = pol.getExpired_at();
        this.choice = pol.getChoice();
    }
    public Poll_noRes() {
        this.id = null;
        this.question = null;
        this.started_at = null;
        this.expired_at = null;
        this.choice = null;
    }

    public void setId(String mid)
    {
        this.id = mid;
    }

    public String getId() {
        return id;
    }

    public void setQuestion(String qn)
    {
        this.question = qn;
    }

    public String getQuestion() {
        return question;
    }
    public String getStarted_at() {
        return started_at;
    }
    public String getExpired_at() {
        return expired_at;
    }
    public String[] getChoice() {
        return choice;
    }

}
