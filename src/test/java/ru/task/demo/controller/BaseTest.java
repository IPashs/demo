package ru.task.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    /**
     * Загрузить файл одной строкой.
     * @param path путь к файлу.
     * @param charset кодирока.
     * @return содержимое файла в виде строки.
     */
    static String readFileAsString(final String path, final Charset charset) {
        try {
            return StreamUtils.copyToString(new ClassPathResource(path).getInputStream(), charset);
        } catch (final Exception exp) {
            throw new RuntimeException("Ошибка загрузки файла" + path);
        }
    }
}
