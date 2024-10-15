package back.SportApp.Training;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training")
public class TrainingController {

    private TrainingService trainingSevice;

    @PostMapping("create")
    public Training createTraining(@RequestBody Training training) {
        return trainingSevice.creer(training);
    }

    // React native
    // Trouver une lib pour avoir des composanbt.
    // pwa progressif web app //






}
