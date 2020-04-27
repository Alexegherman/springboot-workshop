package eu.accesa.training.db;


import eu.accesa.training.model.TestUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * FROM springuser WHERE username = #{username}")
    @ResultType(TestUser.class)
    TestUser findByUsername(@Param("username") String username);
}
