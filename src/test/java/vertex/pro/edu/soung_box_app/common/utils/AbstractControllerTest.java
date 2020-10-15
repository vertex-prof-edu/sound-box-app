package vertex.pro.edu.soung_box_app.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class AbstractControllerTest<T> {

    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(getControllerInstance())
                .setConversionService(createFormattingConversionService())
                .build();
    }

    private FormattingConversionService createFormattingConversionService() {
        return new DefaultFormattingConversionService(true);
    }

    protected MockMvc mockMvc() {
        return mockMvc;
    }

    protected ObjectMapper getMapper() {
        return MAPPER;
    }

    protected abstract T getControllerInstance();
}
