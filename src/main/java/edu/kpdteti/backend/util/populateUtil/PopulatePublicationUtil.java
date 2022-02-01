package edu.kpdteti.backend.util.populateUtil;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Classification;
import edu.kpdteti.backend.entity.Publication;
import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.model.request.publication.PostPublicationRequest;
import edu.kpdteti.backend.util.PostPublicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class PopulatePublicationUtil {

    private final PostPublicationUtil postPublicationUtil;

    @Autowired
    public PopulatePublicationUtil(PostPublicationUtil postPublicationUtil) {
        this.postPublicationUtil = postPublicationUtil;
    }

    public List<Pair<Publication, Classification>> populatePublication(List<Author> authors, List<User> users) {
        List<PostPublicationRequest> requests = getPublicationDataFromCsv(authors, users);
        List<Pair<Publication, Classification>> response = new ArrayList<>();
        requests.forEach(request -> {
            try {
                response.add(postPublicationUtil.postPublication(request));
                log.info("Processing Publication with title: " + request.getPublicationTitle());
            } catch (URISyntaxException | SAXException | IOException | JAXBException e) {
                e.printStackTrace();
            }
        });
        return response;
    }

    private List<PostPublicationRequest> getPublicationDataFromCsv(List<Author> authors, List<User> users) {
        List<PostPublicationRequest> requests = new ArrayList<>();
        // Read CSV
        CSVReader reader;
        Random rand = new Random();
        try {
            // Change the classPath to yours
            reader = new CSVReader(new FileReader(
                    "D:\\Projects\\kpdteti.backend\\src\\main\\resources\\csv\\data_populate.csv"
            ));
            String[] publicationData;
            while ((publicationData = reader.readNext()) != null) {
                // Expecting .csv data with Title, Abstract, Keyword order
                PostPublicationRequest request = PostPublicationRequest.builder()
                        .userId(users.get(rand.nextInt(users.size())).getUserId())
                        .authorIds(getRandomAuthorList(authors))
                        .publicationTitle(publicationData[0])
                        .publicationAbstract(publicationData[1])
                        .publicationKeyword(publicationData[2])
                        .build();
                requests.add(request);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return requests;
    }

    private List<String> getRandomAuthorList(List<Author> authors) {
        List<String> response = new ArrayList<>();
        Random rand = new Random();
        String firstAuthorId = authors.get(rand.nextInt(authors.size())).getAuthorId();
        String secondAuthorId = authors.get(rand.nextInt(authors.size())).getAuthorId();
        if (!firstAuthorId.equals(secondAuthorId)) {
            response.add(firstAuthorId);
            response.add(secondAuthorId);
        } else {
            response.add(firstAuthorId);
        }
        return response;
    }

}
