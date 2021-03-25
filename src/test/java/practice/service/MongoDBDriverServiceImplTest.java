package practice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import practice.model.GameMongoDocument;
import practice.model.PublisherModel;
import practice.repository.GameModel;
import practice.repository.MongoDBJpaRepository;
import practice.transformer.MyGameTransformerImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class MongoDBDriverServiceImplTest {

    @InjectMocks
    private MongoDBDriverService underTest = new MongoDBDriverServiceImpl();

    @Mock
    private MyGameTransformerImpl gameTransformer;

    @Mock
    private MongoDBJpaRepository mongoDBJpaRepository;

    @Captor
    private ArgumentCaptor<GameModel> captor;

    @Test
    void test_ArgumentCaptor() {

        when(gameTransformer.getMongoDocument(create())).thenReturn(GameModel.builder().build());
        when(mongoDBJpaRepository.count()).thenReturn(1l);

        underTest.addGameToMongoDB(create());

        verify(mongoDBJpaRepository).save(captor.capture());
        GameModel captorValue = captor.getValue();

        assertThat(captorValue.getId()).isEqualTo("2");

    }

    private GameMongoDocument create() {
        return GameMongoDocument.builder()
                .id("1")
                .gameGenre("genre")
                .gameName("name")
                .imageUrl("url")
                .gamePublisher(PublisherModel.builder()
                        .name("name")
                        .dev("dev")
                        .build())
                .build();
    }

}