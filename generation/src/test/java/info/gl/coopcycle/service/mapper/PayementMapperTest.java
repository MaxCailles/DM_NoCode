package info.gl.coopcycle.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayementMapperTest {

    private PayementMapper payementMapper;

    @BeforeEach
    public void setUp() {
        payementMapper = new PayementMapperImpl();
    }
}
