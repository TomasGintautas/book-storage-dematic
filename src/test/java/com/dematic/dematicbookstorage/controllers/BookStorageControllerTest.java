package com.dematic.dematicbookstorage.controllers;

import com.dematic.dematicbookstorage.entities.dto.requests.BookRequest;
import com.dematic.dematicbookstorage.entities.dto.requests.BookUpdateRequest;
import com.dematic.dematicbookstorage.entities.dto.responses.BarcodeQuantityResponse;
import com.dematic.dematicbookstorage.entities.dto.responses.BookPriceResponse;
import com.dematic.dematicbookstorage.entities.dto.responses.BookResponse;
import com.dematic.dematicbookstorage.repositories.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookStorageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createBookTest_returnsCreatedSuccessfully() throws Exception {
        BookRequest bookRequest = new BookRequest("41231231231","TestBook","TestAuthor",10L, BigDecimal.valueOf(10.99));

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/v1/book-storage/create")
                .content(objectMapper.writeValueAsString(bookRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        BookResponse bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookResponse.class);

        assertEquals(bookRequest.getBarcode(),bookResponse.getBarcode());
    }

    @Test
    public void createAntiqueBookTest_returnsCreatedSuccessfully() throws Exception {
        BookRequest bookRequest = new BookRequest("41231231231","TestBook","TestAuthor",10L, BigDecimal.valueOf(10.99), Year.of(1990));

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/v1/book-storage/create")
                        .content(objectMapper.writeValueAsString(bookRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        BookResponse bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookResponse.class);

        assertEquals(bookRequest.getReleaseYear(),bookResponse.getReleaseYear());
    }

    @Test
    public void createScienceJournalTest_returnsCreatedSuccessfully() throws Exception {
        BookRequest bookRequest = new BookRequest("41231231231","TestBook","TestAuthor",10L, BigDecimal.valueOf(10.99), 5);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/v1/book-storage/create")
                        .content(objectMapper.writeValueAsString(bookRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        BookResponse bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookResponse.class);

        assertEquals(bookRequest.getScienceIndex(),bookResponse.getScienceIndex());
    }

    @Test
    public void createBookTest_returnsFailedToCreate() throws Exception {
        BookRequest bookRequest = new BookRequest(null,null,"TestAuthor",10L, BigDecimal.valueOf(10.99));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book-storage/create")
                        .content(objectMapper.writeValueAsString(bookRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();

        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    public void getBookTest_returnBookSuccessfully() throws Exception{
        this.postBook();

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/book-storage/{barcode}", "41231231231"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        BookResponse bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookResponse.class);

        assertEquals(bookResponse.getBookName(), "TestBook");
        assertEquals(bookResponse.getBarcode(), "41231231231");
    }

    @Test
    public void updateBookTest_returnUpdatedSuccessfully() throws Exception{
        this.postBook();

        BookUpdateRequest bookUpdateRequest = new BookUpdateRequest("41231231231","UpdatedBook","UpdatedAuthor",9L, BigDecimal.valueOf(10.99));

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/v1/book-storage/{barcode}", "41231231231", bookUpdateRequest)
                        .content(objectMapper.writeValueAsString(bookUpdateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        BookResponse bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookResponse.class);

        assertEquals(bookResponse.getBookName(), bookUpdateRequest.getBookName());
        assertEquals(bookResponse.getBarcode(), bookUpdateRequest.getBarcode());
        assertEquals(bookResponse.getAuthor(), bookUpdateRequest.getAuthor());
        assertEquals(bookResponse.getQuantity(), bookUpdateRequest.getQuantity());
    }

    @Test
    public void calculateBookPriceTest_returnBookWithPriceSuccessfully() throws Exception{
        this.postBook();

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/book-storage/{barcode}/price", "41231231231"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        BookPriceResponse bookPriceResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookPriceResponse.class);

        assertEquals(bookPriceResponse.getTotalPrice(),BigDecimal.valueOf(109.9).setScale(2));
    }

    @Test
    public void calculateBookPriceListTest_returnBarcodeWithPriceSuccessfully() throws Exception{
        this.postBook();

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/book-storage/price-list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<BarcodeQuantityResponse> barcodeQuantityResponseList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<BarcodeQuantityResponse>>(){});

        assertEquals(barcodeQuantityResponseList.size(),1);
    }


    @BeforeEach
    public void deleteAll(){
        bookRepository.deleteAll();
    }

    public void postBook() throws Exception{
        BookRequest bookRequest1 = new BookRequest("41231231231","TestBook","TestAuthor",10L, BigDecimal.valueOf(10.99));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book-storage/create")
                .content(objectMapper.writeValueAsString(bookRequest1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }
}


