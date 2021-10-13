package pojos;

import java.util.List;
import lombok.Data;

public @Data class CreatePetResponse{
	private String name;
	private long id;
	private String status;
	private CreateCategory createCategory;
	private List<CreateTagsItem> tags;
	private List<CreatePhotoUrls> photoUrls;
}