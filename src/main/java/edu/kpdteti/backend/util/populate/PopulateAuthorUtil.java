package edu.kpdteti.backend.util.populate;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.util.IdGeneratorUtil;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopulateAuthorUtil {

    private final IdGeneratorUtil idGeneratorUtil;

    @Autowired
    public PopulateAuthorUtil(IdGeneratorUtil idGeneratorUtil) {
        this.idGeneratorUtil = idGeneratorUtil;
    }

    public List<Author> populateAuthor() {
        List<Pair<String, String>> authorPair = new ArrayList<>();
        authorPair.add(new Pair<>("silmi@ugm.ac.id", "Dr.Eng. Silmi Fauziati, S.T., M.T."));
        authorPair.add(new Pair<>("teguh@ugm.ac.id", "Teguh Bharata Adji, S.T., M.T., M.Eng., Ph.D."));
        authorPair.add(new Pair<>("lukito@ugm.ac.id", "Ir. Lukito Edi Nugroho, M.Sc., Ph.D."));
        authorPair.add(new Pair<>("suning@ugm.ac.id", "Dr. Sri Suning Kusumawardani, S.T., M.T."));
        authorPair.add(new Pair<>("selo@ugm.ac.id", "Selo, S.T., M.T., M.Sc., Ph.D."));
        authorPair.add(new Pair<>("dani@ugm.ac.id", "Dani Adhipta, S.Si., M.T."));
        authorPair.add(new Pair<>("widyawan@ugm.ac.id", "Widyawan, S.T., M.Sc., Ph.D."));

        List<Author> authors = new ArrayList<>();
        authorPair.forEach(item -> {
            Author author = Author.builder()
                    .authorId(idGeneratorUtil.generateId(IdGeneratorEnum.AUTHOR))
                    .authorName(item.getValue())
                    .authorEmail(item.getKey())
                    .authorFaculty("Engineering")
                    .authorUniversity("Universitas Gadjah Mada")
                    .build();
            authors.add(author);
        });
        return authors;
    }
}
