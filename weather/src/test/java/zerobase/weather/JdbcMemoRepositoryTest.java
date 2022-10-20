package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JdbcMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
//@Transactional  -- 테스트에서 사용될 때, 항상 롤백된다. (= 반영이 안 된다)
// (why? 이유는 지연로딩 때문에 - 디폴트가 지연로딩이고 이 경우 한번에 트랜잭션을 flush()해주는 애가 따로 있다. 테스트에서는 flush가 안 된다.)
    // rf. https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional
class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest(){

        // given
        Memo newMemo = new Memo(2, "this is new memo");

        // when
        jdbcMemoRepository.save(newMemo);

        // then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "this is new memo");

    }

    @Test
    void findAllMemoTest(){

        List<Memo> memoList = jdbcMemoRepository.findAll();

        System.out.println(memoList);

        assertNotNull(memoList);

    }

}