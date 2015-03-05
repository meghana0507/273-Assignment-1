package A1;

//import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotNull;

/**
 * Created by Meghana on 3/1/2015.
 */
public class Poll_res {
    private  String id;
    @NotNull
    private  String question;
    @NotNull
    private  String started_at;
    @NotNull
    private  String expired_at;
    @NotNull
    private  String[] choice;

    private  int[] results;

    public Poll_res(String id, String question, String started_at, String expired_at, String[] choice, int[] results) {
        this.id = id;
        this.question = question;
        this.started_at = started_at;
        this.expired_at = expired_at;
        this.choice = choice;
        this.results = results;
       // this.created_by_Moderator = created_by;
    }

    public Poll_res() {
        this.id = null;
        this.question = null;
        this.started_at = null;
        this.expired_at = null;
        this.choice = null;
        this.results = null;
        //this.created_by_Moderator = -1;
    }

    public Poll_res(Poll_res pol2) {

        this.id = pol2.getId();
        this.question = pol2.getQuestion();
        this.started_at = pol2.getStarted_at();
        this.expired_at = pol2.getExpired_at();
        this.choice = pol2.getChoice();
        this.results = pol2.getResults();
        //this.created_by_Moderator = pol2.getCreatedBy();
    }

    public Poll_res(Poll_noRes obj){
        this.id = obj.getId();
        this.question = obj.getQuestion();
        this.choice = obj.getChoice();
        this.expired_at = obj.getExpired_at();
        this.started_at = obj.getStarted_at();
        results = new int[this.choice.length];
        for(int i=0; i<results.length; i++){
            results[i]=0;
        }
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
    //public void setCreatedBy(long modID) { this.created_by_Moderator=modID; }

    public String getQuestion() {
        return question;
    }
    public String getStarted_at() {
        return started_at;
    }
    public String getExpired_at() {
        return expired_at;
    }
//    public long getCreatedBy() {return this.created_by_Moderator; }
    public String[] getChoice() {
        return choice;
    }

    public int[] getResults() {
        return results;
    }
    public void incrementResults(int i)
    {
        this.results[i]++;
    }
}

