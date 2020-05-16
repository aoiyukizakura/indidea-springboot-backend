package com.mirai.indidea.service.serviceImpl;

import com.mirai.indidea.dao.*;
import com.mirai.indidea.dto.Userdto.LoginDto;
import com.mirai.indidea.dto.Userdto.UserRegisterDto;
import com.mirai.indidea.dto.Userdto.UserUpdateDto;
import com.mirai.indidea.entity.*;
import com.mirai.indidea.service.UserService;
import com.mirai.indidea.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ApplyRepository applyRepository;

    /**
     * 查找用户详细信息
     * @param id 用户id
     * @return User
     */
    @Override
    public User find(int id) {
        return userRepository.findUserById(id);
    }

    /**
     * 用户登录
     * @param logindto 用户登录信息
     * @return User
     */
    @Override
    public User login(LoginDto logindto) {
        return userRepository
                .findUserByEmailAndPasswordAndStatusNot(
                logindto.getEmail(),
                MD5Utils.crypt(logindto.getPassword()), 0);
    }

    /**
     * save 用户注册 -> 先查找是否存在邮件，无则新增，有就报错
     * @param userRegisterDto 用户注册信息
     * @return User
     */
    @Override
    public boolean save(UserRegisterDto userRegisterDto) {
        if (userRepository.findUserByEmail(userRegisterDto.getEmail()) == null) {
            User user = new User();
            user.setUsername(userRegisterDto.getUsername());
            user.setPassword(MD5Utils.crypt(userRegisterDto.getPassword()));
            user.setEmail(userRegisterDto.getEmail());
            userRepository.saveAndFlush(user);
            return true;
        } else {
            return false;
        }

    }

    /**
     * 用户更新信息
     * @param userUpdateDto 用户更新信息数据
     * @return boolean
     */
    @Override
    public boolean update(@Valid UserUpdateDto userUpdateDto, int id) {
        User u = userRepository.findUserById(id);
        if (userUpdateDto.getUsername() != null)
            u.setUsername(userUpdateDto.getUsername());
        if (userUpdateDto.getWebsite() != null)
            u.setWebsite(userUpdateDto.getWebsite());
        if (userUpdateDto.getAddress() != null)
            u.setAddress(userUpdateDto.getAddress());
        if (userUpdateDto.getDes() != null)
            u.setDes(userUpdateDto.getDes());
        if (userUpdateDto.getAvatar() != null)
            u.setAvatar(userUpdateDto.getAvatar());
        if (userUpdateDto.getPassword() != null)
            u.setPassword(MD5Utils.crypt(userUpdateDto.getPassword()));
        userRepository.saveAndFlush(u);
        return true;
    }


    @Override
    public boolean logout(HttpServletRequest request) {
        return true;
    }

    /**
     * 更新密码
     * */
    @Override
    public boolean changePassword(int id, String password) {
        User u = userRepository.findUserById(id);
        u.setPassword(MD5Utils.crypt(password));
        userRepository.saveAndFlush(u);
        return true;
    }

    /**
     * 禁用用户
     * @param id 用户id
     * @return boolean
     */
    @Override
    public boolean delete(int id) {
        User user = userRepository.findUserById(id);
        user.setStatus(0);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public List<Project> findMyProject(Integer id) {
        return projectRepository.findProjectByOwnerIdAndStatusNot(id,4);
    }

    @Override
    public List<Point> pointList(int userId) {
        return pointRepository.findByUserIdOrderByUpdatedatDesc(userId);
    }

    @Override
    public List<Point> pointList(int idInRequest, Pageable pageable) {
        return pointRepository.findByUserIdOrderByUpdatedatDesc(idInRequest, pageable);
    }

    @Override
    public boolean addPoint(int userId, int point) {
        try {
            if (point <= 0) {
                return false;
            }
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            String order = "top-up-balance-point-" + point + "-user-" + userId + "-time-" + ft.format(dNow);
            User user = userRepository.findUserById(userId);
            int balance = user.getBalance();
            balance += point;
            user.setBalance(balance);
            Point point1 = new Point();
            point1.setUser(user);
            point1.setPoint(point);
            point1.setOrdernumber(order);
            userRepository.saveAndFlush(user);
            pointRepository.saveAndFlush(point1);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public long supportNum(int userId) {
        return sponsorRepository.countBySponsorId(userId);
    }

    @Override
    public List<Favorite> myFavProject(int userId, Pageable pageable) {
        return favoriteRepository.findByUserId(userId, pageable);
    }

    @Override
    public long myFavProject(int userId) {
        return favoriteRepository.countByUserId(userId);
    }

    @Override
    public List<Sponsor> muSupport(int userId) {
        return sponsorRepository.findBySponsorId(userId);
    }

    @Override
    public List<Sponsor> supportHistory(int projectId, int idInRequest) {
        return sponsorRepository.findByProjectIdAndSponsorId(projectId, idInRequest);
    }

    @Override
    public boolean getApplyStatus(int idInRequest) {
        try {
            return applyRepository.countByStatusAndUserId(1, idInRequest) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean doApply(Integer idInRequest) {
        try {
            Apply apply = new Apply();
            User user = new User();
            user.setId(idInRequest);
            apply.setUser(user);
            applyRepository.saveAndFlush(apply);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Apply> myApplyList(int user_id) {
        return applyRepository.findAllByUserIdAndStatusNot(user_id, 1);
    }
}
