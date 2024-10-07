package tableordering.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "menu", url = "${api.url.menu}")
public interface MenuListService {
    @GetMapping(path = "/menuLists")
    public List<MenuList> getMenuList();

    @GetMapping(path = "/menuLists/{id}")
    public MenuList getMenuList(@PathVariable("id") Long id);
}
