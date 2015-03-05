package A1;

/**
 * Created by Meghana on 2/27/2015.
 */

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.validation.Valid;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/v1")
public class ModController
{

    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong pollCounter = new AtomicLong(27072);
    private HashMap<Long, Moderator> ModList = new HashMap<Long, Moderator>();
    private HashMap<Long, ArrayList<Poll_res>> PollModList = new HashMap<Long, ArrayList<Poll_res>>();
    private HashMap<String, Poll_noRes> PollNoResList = new HashMap<String, Poll_noRes>();
    private HashMap<String, Poll_res> PollResList = new HashMap<String, Poll_res>();
    private HashMap<String, Long> PollList = new HashMap<String, Long>();

//    private String str = null;
    SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

    @RequestMapping(value="/moderators",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Moderator createModerator(@RequestBody @Valid Moderator mod)
    {
                    mod.setId(counter.incrementAndGet());
                    Date temp = new Date();
                    mod.setCreated_at(ISO8601DATEFORMAT.format(temp));
                    ModList.put(mod.getId(), mod);
                    return mod;

    }

    @RequestMapping(value="/moderators/{moderator_id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Moderator viewModerator(@PathVariable("moderator_id") long modId) {
            Moderator mod = ModList.get(modId);
            return mod;
    }

    @RequestMapping(value="/moderators/{moderator_id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Moderator updateModerator(@PathVariable("moderator_id") long modId,
                                                   @RequestBody Moderator modUpdated)
    {
            Moderator mod = ModList.get(modId);
            if (modUpdated.getName() != null) {
                mod.setName(modUpdated.getName());
            }

            if (modUpdated.getEmail() != null) {
                mod.setEmail(modUpdated.getEmail());
            }

            if (modUpdated.getPassword() != null) {
                mod.setPassword(modUpdated.getPassword());
            }

            ModList.put(mod.getId(), mod);

            return mod;

    }

    @RequestMapping(value="/moderators/{moderator_id}/polls",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Poll_noRes createPoll(@PathVariable("moderator_id") long modId, @RequestBody @Valid Poll_noRes pol) {
        if(ModList.containsKey(modId)) {
            pol.setId(Long.toString(pollCounter.incrementAndGet(), 36).toUpperCase());
            PollNoResList.put(pol.getId(), pol);
            Poll_res pol2 = new Poll_res(pol);
            PollResList.put(pol2.getId(), pol2);
            ArrayList<Poll_res> PollArray = new ArrayList<Poll_res>();

            PollList.put(pol.getId(), modId);

            if (PollModList.containsKey(modId)) {
                PollModList.get(modId).add(pol2);
            } else {
                PollArray.add(pol2);
                PollModList.put(modId, PollArray);
            }
            return pol;
        }
        else
            return null;
    }

    @RequestMapping(value="/polls/{poll_id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Poll_noRes viewPollNoRes(@PathVariable("poll_id") String polId){
        Poll_noRes pol = PollNoResList.get(polId);
        return pol;
    }

    @RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Poll_res viewPollRes(@PathVariable("moderator_id") long modId, @PathVariable("poll_id") String polId)
    {

       Poll_res pol2 = PollResList.get(polId);
        return pol2;
    }

    @RequestMapping(value="/moderators/{moderator_id}/polls",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArrayList<Poll_res> ListPolls(@PathVariable("moderator_id") long modId) {
              ArrayList<Poll_res> temp =  PollModList.get(modId);
                return temp;
    }

   @RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}",method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePoll(@PathVariable("moderator_id") long modId, @PathVariable("poll_id") String polId)
   {
        Poll_res pol2 = PollResList.get(polId);
        ArrayList<Poll_res> temp= PollModList.get(modId);
        int i = temp.indexOf(pol2);
        temp.remove(i);
        PollModList.put(modId, temp);
        PollNoResList.remove(polId);
        PollResList.remove(polId);
        PollList.remove(polId);
    }

    @RequestMapping(value="/polls/{poll_id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void votePoll(@PathVariable("poll_id") String polId, @RequestParam(value="choice") int choice)
    {
            Poll_res pol2 = PollResList.get(polId);
            long modId = PollList.get(polId);
            ArrayList<Poll_res> PollArray = PollModList.get(modId);
            PollArray.get(PollArray.indexOf(pol2)).incrementResults(choice);
            PollModList.put(modId, PollArray);
            PollResList.put(polId, pol2);

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<String>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        return errors;
    }

} // End class
