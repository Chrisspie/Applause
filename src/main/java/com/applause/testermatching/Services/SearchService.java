package com.applause.testermatching.Services;

import com.applause.testermatching.Model.Bug;
import com.applause.testermatching.Model.SearchCriteria;
import com.applause.testermatching.Model.Tester;
import com.applause.testermatching.Repositories.BugRepository;
import com.applause.testermatching.Repositories.DeviceRepository;
import com.applause.testermatching.Repositories.TesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {
    private final DeviceRepository deviceRepository;
    private final TesterRepository testerRepository;
    private final BugRepository bugRepository;

    @Autowired
    public SearchService(DeviceRepository deviceRepository, TesterRepository testerRepository, BugRepository bugRepository) {
        this.deviceRepository = deviceRepository;
        this.testerRepository = testerRepository;
        this.bugRepository = bugRepository;
    }

    public List<Tester> findByCountryAndDevice(SearchCriteria searchCriteria) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory();

        List<String> searchCriteriaCountry = searchCriteria.getCountry();
        List<String> searchCriteriaDevice = searchCriteria.getDevice();
        List<Tester> testers;

        Bug bug = bugRepository.findById(1L).get();

        if(searchCriteriaCountry.contains("ALL") && searchCriteriaDevice.contains("ALL")){
            testers=testerRepository.findAll();
        }else{//todo co w przypadku gdy country jest ALL ale device jest sprecyzowane
            EntityManager em = emf.createEntityManager();
            List a = em.createQuery("SELECT u.tester_Id, u.first_name from Tester u INNER JOIN TESTER_DEVICE td ON u.tester_Id=td.tester_Id INNER JOIN DEVICE d ON td.device_id=d.device_id").getResultList();
            int i=0;


            //testers=testerRepository.findXYZ();
        }


        //todo przeszukać tabelę BUG i sprawdzać czy zawiera ID Testera i ID Device i zliczyć kto ile ma
      /*  ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("country", match -> match.ignoreCase().contains())
                .withMatcher("device", match -> match.ignoreCase().contains());
*/

        return null;
    }
}
