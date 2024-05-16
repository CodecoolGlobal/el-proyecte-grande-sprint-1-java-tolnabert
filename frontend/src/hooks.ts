import { useState, useEffect } from "react";

type State<T> = {
  data: T | null;
  error: Error | null;
  isLoading: boolean;
};

const useFetch = <T>(url: string, token: string | null): State<T> => {
  const [data, setData] = useState<T | null>(null);
  const [error, setError] = useState<Error | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const headers = {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        };

        const response = await fetch(url, {
          headers,
        });
        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }
        const result = await response.json();
        setData(result);
      } catch (error) {
        setError(error as Error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [url, token]);

  return { data, error, isLoading };
};

export default useFetch;
