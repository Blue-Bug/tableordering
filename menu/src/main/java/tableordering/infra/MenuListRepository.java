package tableordering.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tableordering.domain.*;

@RepositoryRestResource(collectionResourceRel = "menuLists", path = "menuLists")
public interface MenuListRepository
    extends PagingAndSortingRepository<MenuList, Long> {}
