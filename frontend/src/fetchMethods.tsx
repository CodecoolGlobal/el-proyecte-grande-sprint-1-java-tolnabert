import { fetchPatchPwChangeAuthParams } from "./utils/types";

export async function fetchPatchPwChangeAuth({
  url,
  jwtToken,
  newPassword,
}: fetchPatchPwChangeAuthParams) {
  const response = await fetch(url, {
    method: "PATCH",
    headers: {
      Authorization: `Bearer ${jwtToken}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ newPassword }),
  });
  return response;
}
