/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class JNImethods */

#ifndef _Included_JNImethods
#define _Included_JNImethods
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     JNImethods
 * Method:    setBoard
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_JNImethods_setBoard
  (JNIEnv *, jobject, jint);

/*
 * Class:     JNImethods
 * Method:    makeMove
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_JNImethods_makeMove
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     JNImethods
 * Method:    getValue
 * Signature: (II)C
 */
JNIEXPORT jchar JNICALL Java_JNImethods_getValue
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     JNImethods
 * Method:    checkWin
 * Signature: (C)Z
 */
JNIEXPORT jboolean JNICALL Java_JNImethods_checkWin
  (JNIEnv *, jobject, jchar);

/*
 * Class:     JNImethods
 * Method:    checkDraw
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_JNImethods_checkDraw
  (JNIEnv *, jobject);

/*
 * Class:     JNImethods
 * Method:    destroyBoard
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_JNImethods_destroyBoard
  (JNIEnv *, jobject);

/*
 * Class:     JNImethods
 * Method:    capture
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_JNImethods_capture
  (JNIEnv *, jobject, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
