/*
 * Created on 2019-12-26 ( Time 13:27:37 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package dizertatie.be.dizertatie.domain.repository;

import dizertatie.be.dizertatie.domain.bean.Account;
import dizertatie.be.dizertatie.domain.bean.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Spring JPA Repository for OriLogs
 * 
 * @author Telosys Tools Generator
 *
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    @Query(value = "select * from course where id not in (select course_id from account_x_course axc where account_id = :accountId);", nativeQuery = true)
    List<Course> findNotEnrolledCourses(@Param("accountId") Long accountId);

}