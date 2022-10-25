package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JpaMemoRepository;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest(){
        // given
        Memo newMemo = new Memo(10, "this is jpa memo");

        // when
        jpaMemoRepository.save(newMemo);

        // then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);
    }

    @Test
    void findByIdTest(){
        // given
        Memo newMemo = new Memo(11, "jpa");

        // when
        Memo memo = jpaMemoRepository.save(newMemo);
        System.out.println(memo.getId());

        // then
        Optional<Memo> result = jpaMemoRepository.findById(memo.getId());
        assertEquals(result.get().getText(), "jpa");

        // !! false -- why? GeneratedValue로 IDENTITY를 넣었기 때문에,
        // 11을 넣어주었어도, 테이블에서 정해준 pk 제약조건 (ex. auto_increment)에 따라 생성된다.
//        Optional<Memo> result = jpaMemoRepository.findById(11);
//        assertEquals(result.get().getText(), "jpa");
    }

}
