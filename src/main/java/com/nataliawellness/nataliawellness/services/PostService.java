package com.nataliawellness.nataliawellness.services;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Post;
import com.nataliawellness.nataliawellness.entities.Setting;
import com.nataliawellness.nataliawellness.entities.User;
import com.nataliawellness.nataliawellness.helpers.SiteHelper;
import com.nataliawellness.nataliawellness.repositories.CategoryRepository;
import com.nataliawellness.nataliawellness.repositories.PostRepository;
import com.nataliawellness.nataliawellness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void create(Post post) {

        User user = userRepository.findByUsername("admin");
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());
        post.setAuthor(user);


        postRepository.save(post);
    }

    public Post getById(Long id) {

        return postRepository.getOne(id);

    }

    public void update(Post article) {

        article.setUpdatedAt(new Date());
        postRepository.save(article);
    }

    public String uploadFile(MultipartFile file){

        List<String> allowedFileTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

        String filename = file.getOriginalFilename();

        System.out.println(filename);

        if(filename.isEmpty()){
            return null;
        }

        String extension = filename.substring(filename.lastIndexOf("."), filename.length());
        String filepath = Paths.get(uploadPath, filename).toString();
        String fileContentType = file.getContentType();


        if(!allowedFileTypes.contains(fileContentType)){
            return null;
        }

        File file1 = new File(filepath);
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file1));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String newFileName = SiteHelper.encodeForUrl(filename)+"."+extension;
        String newfilepath = Paths.get(uploadPath, newFileName).toString();
        file1.renameTo(new File(newfilepath));

        return newFileName;

    }

    public boolean isDuplicateSlug(Post article) {

        //return (postRepository.findBySlugAndIdNot(article.getSlug(),article.getId()) == null) ? false : true;

        return false;
    }

    public List<Post> getAllByHome() {
        return postRepository.findAll();
    }

    public List<Post> findbyKeyword(String query) {

        if(query == null || query.length() == 0) return null;

        return postRepository.findByTitleContaining(query);
    }

    public Post getBySlug(String slug) {

        return postRepository.findBySlug(slug);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getHomePagePosts() {


        return postRepository.findByShowOnHomeEqualsAndStatus(true , true);

//        List<String> slugList = new ArrayList<String>();
//        List<Setting> settings = settingService.getAllByName("home_page_post");
//        for(Setting setting : settings){
//            slugList.add(setting.getValue());
//        }
       // return postRepository.findBySlugIn(slugList);

    }

    public List<Post> getTopBlogInCategory(int i, Category category) {

        //get posts in category
        //status = enable
        //limit by number
        //order by createdat

        return postRepository.findAllByCategoriesContains(category);
    }
}
