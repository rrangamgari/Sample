package com.example.demo.web;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;



/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@RequestMapping("/sample")
@RestController public class UserRestController {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static final String key        = "aesEncryptionKey";
  private static final String initVector = "encryptionIntVec";

  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  Logger logger = LoggerFactory.getLogger(UserRestController.class);

  /** DOCUMENT ME! */


  @Autowired private UserService userService;


  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   encrypted  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public static String decrypt(String encrypted) {
    try {
      IvParameterSpec iv       = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec   skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

      byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

      return new String(original);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   value  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public static String encrypt(String value) {
    try {
      IvParameterSpec iv       = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec   skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
      Cipher          cipher   = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

      byte[] encrypted = cipher.doFinal(value.getBytes());

      return Base64.encodeBase64String(encrypted);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   id  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @RequestMapping(
    value  = "/users/{id}",
    method = RequestMethod.DELETE
  )
  public boolean deleteUser(@PathVariable("id") Long id) {
    // model.addAttribute("userForm", new User());
    logger.info("id : " + id);

    userService.delete(id);

    return true;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   id  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @RequestMapping(
    value  = "/users/{id}",
    method = RequestMethod.GET
  )
  public User getUsers(@PathVariable("id") Long id) {
    // model.addAttribute("userForm", new User());
    logger.info("id : " + id);

    User user = userService.findById(id);

    return user;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  user  DOCUMENT ME!
   */
  @RequestMapping(
    value  = "/users/{id}",
    method = RequestMethod.POST
  )
  public void save(User user) {
    user.setPassword(encrypt(user.getPassword()));

    // user.setRoles(new HashSet<>(roleRepository.findAll()));
    userService.save(user);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   id  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @RequestMapping(
    value  = "/users/{id}",
    method = RequestMethod.PUT
  )
  public User updateUsers(@PathVariable("id") Long id) {
    // model.addAttribute("userForm", new User());
    logger.info("id : " + id);

    User user = userService.findById(id);

    return user;
  }

} // end class UserRestController
