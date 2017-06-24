package fga.mds.gpp.trezentos.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.util.ArrayList;
import java.util.HashMap;
import fga.mds.gpp.trezentos.BuildConfig;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.View.EvaluationFragment;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class EvaluationControlUnitTest {

    private EvaluationControl evaluationControl;
    private EvaluationFragment fragment = new EvaluationFragment();

    @Test
    public void shouldValidateSendEvaluation(){
        evaluationControl = EvaluationControl.getInstance(fragment.getContext());

        String examName, email, userClassName;
        HashMap<String, Integer> groups = new HashMap<>();
        HashMap<String, Double> grades = new HashMap<>();
        Double cutOff;

        groups.put("teste@email.com", 1);
        groups.put("testandosedamerda@email.com", 1);
        groups.put("agora@da.com", 1);
        groups.put("gui.988@hotmail.com", 1);

        grades.put("teste@email.com", 8.0);
        grades.put("testandosedamerda@email.com", 6.0);
        grades.put("agora@da.com", 2.0);
        grades.put("gui.988@hotmail.com", 1.0);

        examName = "P1";
        email = "teste@email.com";
        userClassName = "testeteste";
        cutOff = 5.0;

        boolean test =
                evaluationControl.sendEvaluation(examName, email,
                        userClassName, groups, grades, cutOff);

        assertEquals(test, false);
    }

    @Test
    public void shouldValidateSendEvaluationWithNullVariables(){
        evaluationControl = EvaluationControl.getInstance(fragment.getContext());

        String examName, email;
        Double cutOff;
        HashMap<String, Integer> groups = new HashMap<>();
        HashMap<String, Double> grades = new HashMap<>();

        groups.put("teste@email.com", 1);
        groups.put("testandosedamerda@email.com", 1);
        groups.put("agora@da.com", 1);
        groups.put("gui.988@hotmail.com", 1);

        grades.put("teste@email.com", 8.0);
        grades.put("testandosedamerda@email.com", 6.0);
        grades.put("agora@da.com", 2.0);
        grades.put("gui.988@hotmail.com", 1.0);

        examName = "P1";
        email = "teste@email.com";
        cutOff = 5.0;

        boolean test =
                evaluationControl.sendEvaluation(examName, email, null, groups, grades, cutOff);

        assertEquals(test, false);
    }

    @Test
    public void shouldGetEvaluations(){
        evaluationControl = EvaluationControl.getInstance(fragment.getContext());
        ArrayList<Evaluation> evaluationList;
        String email = "teste@email.com";
        UserAccount userAccount = new UserAccount();
        try {
            userAccount.setEmail(email);
        } catch (UserException e) {
            e.printStackTrace();
        }

        evaluationList = evaluationControl.getEvaluations(userAccount);

        assertNotNull(evaluationList);
    }

}
