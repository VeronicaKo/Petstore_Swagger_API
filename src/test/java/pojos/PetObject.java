package pojos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetObject{
	private String name;
	private int id;
	private String status;
	private CreateCategory category;
	private List<CreateTagsItem> tags;
	private List<String> photoUrls;
}