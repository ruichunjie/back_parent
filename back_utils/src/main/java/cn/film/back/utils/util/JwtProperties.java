package cn.film.back.utils.util;

import lombok.Data;

/**
 * description: JWT
 * create_time: 2019/12/5
 * writed by xinyue
 */
@Data
public class JwtProperties {

    private static JwtProperties jwtProperties = new JwtProperties();
    private JwtProperties(){}
    public static JwtProperties getJwtProperties(){
        return jwtProperties;
    }

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 604800L;

    private String authPath = "login";

    private String md5Key = "randomKey";

}
