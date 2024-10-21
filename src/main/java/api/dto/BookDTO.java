package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the Book Schema in https://fakerestapi.azurewebsites.net/index.html
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String dateTime;

    public static BookDTO getDefaultWithRandomId() {
        return new BookDTO(
                (int) (Math.random() * 9_000_000) + 1_000_000, // Generate random number from 1,000,000 to 9,999,999
                "QA Title",
                "QA Description",
                300,
                "QA Excerpt",
                "2024-01-01T10:00:00"
        );
    }

}
